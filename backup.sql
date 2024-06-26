PGDMP     1    1                |            kaanbooking    14.12    14.12                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16814    kaanbooking    DATABASE     V   CREATE DATABASE kaanbooking WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE kaanbooking;
                postgres    false            �            1259    16823    hotel    TABLE     C  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name character varying(50) NOT NULL,
    city character varying(25) NOT NULL,
    region character varying(25) NOT NULL,
    address character varying(75) NOT NULL,
    email character varying(75) NOT NULL,
    phone character varying(50) NOT NULL,
    star character varying(25) NOT NULL,
    freeparking boolean NOT NULL,
    freewifi boolean NOT NULL,
    swimmingpool boolean NOT NULL,
    gym boolean NOT NULL,
    concierge boolean NOT NULL,
    spa boolean NOT NULL,
    roomservice boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    16822    hotel_hotel_id_seq    SEQUENCE     �   CREATE SEQUENCE public.hotel_hotel_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.hotel_hotel_id_seq;
       public          postgres    false    212                       0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
          public          postgres    false    211            �            1259    16830    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_name character varying(100) NOT NULL,
    hotel_name character varying(75) NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    16829    pension_pension_id_seq    SEQUENCE     �   CREATE SEQUENCE public.pension_pension_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.pension_pension_id_seq;
       public          postgres    false    214                       0    0    pension_pension_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.pension_pension_id_seq OWNED BY public.pension.pension_id;
          public          postgres    false    213            �            1259    16816    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_username character varying(25) NOT NULL,
    user_password character varying(25) NOT NULL,
    user_role character varying(10) NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    16815    user_user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.user_user_id_seq;
       public          postgres    false    210                       0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    209            q           2604    16826    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    212    211    212            r           2604    16833    pension pension_id    DEFAULT     x   ALTER TABLE ONLY public.pension ALTER COLUMN pension_id SET DEFAULT nextval('public.pension_pension_id_seq'::regclass);
 A   ALTER TABLE public.pension ALTER COLUMN pension_id DROP DEFAULT;
       public          postgres    false    214    213    214            p           2604    16819    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    209    210    210                      0    16823    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, city, region, address, email, phone, star, freeparking, freewifi, swimmingpool, gym, concierge, spa, roomservice) FROM stdin;
    public          postgres    false    212   �       
          0    16830    pension 
   TABLE DATA           Q   COPY public.pension (pension_id, hotel_id, pension_name, hotel_name) FROM stdin;
    public          postgres    false    214   *                 0    16816    user 
   TABLE DATA           R   COPY public."user" (user_id, user_username, user_password, user_role) FROM stdin;
    public          postgres    false    210   j                  0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 1, true);
          public          postgres    false    211                       0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 1, true);
          public          postgres    false    213                       0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 11, true);
          public          postgres    false    209            v           2606    16828    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    212            x           2606    16835    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    214            t           2606    16821    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    210            y           2606    16836    pension pension_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 G   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_hotel_id_fkey;
       public          postgres    false    3446    214    212               \   x�3��NL�S��/I���M��,N�,�+-N�-J��g�)��r3��� =z����F�f
F��
�&
�f�!A���%@�#�b���� ��      
   0   x�3�4��		r�w����s�	�s��NL�S��/I������ �+
�         ^   x�3�,.-H-rL����44261�L���8�s2��LL���KN��41ANW� �HWW.CC�Z(������e�Y�Z\4	d��+����� e�     