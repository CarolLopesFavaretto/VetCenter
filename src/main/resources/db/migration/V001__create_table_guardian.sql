CREATE TABLE public.guardian (
	id bigserial NOT NULL,
	"name" varchar(50) NOT NULL,
	cpf varchar(255) NOT NULL,
	telephone bigserial NOT NULL,
	CONSTRAINT guardian_pkey PRIMARY KEY (id)
);