CREATE TABLE IF NOT EXISTS contact (
  id serial NOT NULL,
  phone_number character varying(20) NOT NULL,
  CONSTRAINT contact_pkey PRIMARY KEY (id),
  CONSTRAINT uk_898atepo5gx8dqj60c07k766b UNIQUE (phone_number)
)