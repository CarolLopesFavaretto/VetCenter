CREATE TABLE public.consultation (
	id bigserial NOT NULL,
	name_veterinary varchar(50) NOT NULL,
	value numeric(19, 2) NOT NULL,
	cause varchar(150) NOT NULL,
	observations varchar(255) NOT NULL,
	"date" timestamp NOT NULL,
	regress bool NOT NULL,
	prescription_id bigserial NOT NULL,
	CONSTRAINT consultation_pkey PRIMARY KEY (id)
);

-- public.consultation foreign keys

ALTER TABLE public.consultation ADD CONSTRAINT consultation_prescription_id_fkey FOREIGN KEY (prescription_id) REFERENCES public.prescription(id);