-- Drop table

-- DROP TABLE public.review;

CREATE TABLE public.review (
	id uuid NOT NULL,
	product_id uuid NULL,
	reviewer varchar(255) NULL,
	"text" varchar(255) NULL,
	CONSTRAINT review_pkey PRIMARY KEY (id)
);
