INSERT INTO users (
  id,
  email,
  password,
  roles
)
VALUES (
  'aa99aaaa-9a9a-9a99-9a9a-a99999aa9aa9',
  'admin@exemple.com',
  '$2a$10$JY25NlNCDJmPocI4UwUUvO7df9qExkNbhGdkVDn4b9NthivJ7pLiO',
  ARRAY['CLIENT','ADMIN']::user_role_enum[]
);