CREATE TABLE public.animal (
	id bigserial NOT NULL,
	"name" varchar(50) NOT NULL,
	"type" varchar(50) NOT NULL,
	age int4 NOT NULL,
	race varchar(50) NOT NULL,
	guardian_id bigserial NOT NULL,
	CONSTRAINT animal_pkey PRIMARY KEY (id)
);

ALTER TABLE public.animal ADD CONSTRAINT animal_guardian_id_fkey FOREIGN KEY (guardian_id) REFERENCES public.guardian(id);