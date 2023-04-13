CREATE TABLE public.prescription (
	id bigserial NOT NULL,
	medication varchar(100) NOT NULL,
	"date" timestamp NOT NULL,
	consultation_id bigserial NOT NULL,
	CONSTRAINT prescription_pkey PRIMARY KEY (id)
);

-- public.prescription foreign keys

ALTER TABLE public.prescription ADD CONSTRAINT prescription_consultation_id_fkey FOREIGN KEY (consultation_id) REFERENCES public.consultation(id);