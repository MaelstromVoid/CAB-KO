package cabko.repository.products

import cabko.repository.ProductRepository
import common.identity.ProductID
import core.products.entity.Product
import generated.tables.Products.PRODUCTS
import generated.tables.records.ProductsRecord
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.springframework.stereotype.Repository

@Repository
class JooqProductRepository(private val jooq: DSLContext) : ProductRepository {

    override fun findById(productID: ProductID): Product? {
        val record = jooq.selectFrom(PRODUCTS)
            .where(PRODUCTS.ID.eq(productID.id))
            .fetchOne() ?: return null
        return mapping(record)
    }

    override fun findAll(page: Int, size: Int, nameFilter: String?): List<Product> {
        return jooq.selectFrom(PRODUCTS)
            .where(
                if (!nameFilter.isNullOrBlank()) {
                    PRODUCTS.NAME.containsIgnoreCase(nameFilter)
                } else DSL.trueCondition()
            )
            .limit(size).offset(page * size)
            .fetch { record ->
                mapping(record)
            }
    }

    override fun save(product: Product): Product {
        jooq.insertInto(PRODUCTS)
            .columns(
                PRODUCTS.ID,
                PRODUCTS.NAME,
                PRODUCTS.DESCRIPTION,
                PRODUCTS.PRICE,
                PRODUCTS.QUANTITY
            )
            .values(
                product.productID.id,
                product.name,
                product.description,
                product.price,
                product.quantity
            )
            .execute()
        return product
    }

    override fun deleteById(productID: ProductID): Boolean {
        return jooq.deleteFrom(PRODUCTS)
            .where(PRODUCTS.ID.eq(productID.id))
            .execute() == 1
    }

    override fun decrementStock(productID: ProductID, amount: Int): Product? =
        jooq.update(PRODUCTS)
            .set(PRODUCTS.QUANTITY, PRODUCTS.QUANTITY.minus(amount))
            .where(PRODUCTS.ID.eq(productID.id))
            .and(PRODUCTS.QUANTITY.minus(amount).ge(0))
            .returning()
            .fetchOne()
            ?.let { record -> mapping(record) }


    private fun mapping(record: ProductsRecord): Product =
        Product(
            productID = ProductID(record[PRODUCTS.ID]),
            name = record[PRODUCTS.NAME],
            description = record[PRODUCTS.DESCRIPTION],
            price = record[PRODUCTS.PRICE],
            quantity = record[PRODUCTS.QUANTITY]
        )
}
