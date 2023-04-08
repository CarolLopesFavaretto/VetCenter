CREATE TABLE public.prescription (
	id bigserial NOT NULL,
	medication varchar(100) NOT NULL,
	"date" timestamp NOT NULL,
	CONSTRAINT prescription_pkey PRIMARY KEY (id)
);