PGDMP         #                |            kaanbooking    14.12    14.12 5    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    16814    kaanbooking    DATABASE     V   CREATE DATABASE kaanbooking WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'C';
    DROP DATABASE kaanbooking;
                postgres    false            �            1259    16907    booking    TABLE     �  CREATE TABLE public.booking (
    booking_id integer NOT NULL,
    room_id integer NOT NULL,
    customer_name character varying(50) NOT NULL,
    customer_tc character varying(11) NOT NULL,
    customer_mail character varying(100) NOT NULL,
    customer_phone character varying(15) NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    child_number integer DEFAULT 0,
    adult_number integer DEFAULT 0,
    total_price numeric(10,2) NOT NULL,
    customer_note character varying(100)
);
    DROP TABLE public.booking;
       public         heap    postgres    false            �            1259    16906    booking_booking_id_seq    SEQUENCE     �   CREATE SEQUENCE public.booking_booking_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.booking_booking_id_seq;
       public          postgres    false    221            7           0    0    booking_booking_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.booking_booking_id_seq OWNED BY public.booking.booking_id;
          public          postgres    false    220            �            1259    16823    hotel    TABLE     C  CREATE TABLE public.hotel (
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
       public          postgres    false    212            8           0    0    hotel_hotel_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.hotel_hotel_id_seq OWNED BY public.hotel.hotel_id;
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
       public          postgres    false    214            9           0    0    pension_pension_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.pension_pension_id_seq OWNED BY public.pension.pension_id;
          public          postgres    false    213            �            1259    16885    room    TABLE     R  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    room_type character varying(25) NOT NULL,
    room_stock integer NOT NULL,
    bed_num integer NOT NULL,
    square_meters double precision NOT NULL,
    tv boolean NOT NULL,
    mini_bar boolean NOT NULL,
    game_console boolean NOT NULL,
    hotel_safe boolean NOT NULL,
    projection boolean NOT NULL,
    child_price double precision NOT NULL,
    adult_price double precision NOT NULL,
    room_price double precision NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    16884    room_room_id_seq    SEQUENCE     �   CREATE SEQUENCE public.room_room_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.room_room_id_seq;
       public          postgres    false    219            :           0    0    room_room_id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.room_room_id_seq OWNED BY public.room.room_id;
          public          postgres    false    218            �            1259    16842    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    hotel_name character varying(100) NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    16841    season_season_id_seq    SEQUENCE     �   CREATE SEQUENCE public.season_season_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.season_season_id_seq;
       public          postgres    false    216            ;           0    0    season_season_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.season_season_id_seq OWNED BY public.season.season_id;
          public          postgres    false    215            �            1259    16855    season_season_id_seq1    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq1
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            �            1259    16816    user    TABLE     �   CREATE TABLE public."user" (
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
       public          postgres    false    210            <           0    0    user_user_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.user_user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    209            �           2604    16910    booking booking_id    DEFAULT     x   ALTER TABLE ONLY public.booking ALTER COLUMN booking_id SET DEFAULT nextval('public.booking_booking_id_seq'::regclass);
 A   ALTER TABLE public.booking ALTER COLUMN booking_id DROP DEFAULT;
       public          postgres    false    221    220    221            �           2604    16826    hotel hotel_id    DEFAULT     p   ALTER TABLE ONLY public.hotel ALTER COLUMN hotel_id SET DEFAULT nextval('public.hotel_hotel_id_seq'::regclass);
 =   ALTER TABLE public.hotel ALTER COLUMN hotel_id DROP DEFAULT;
       public          postgres    false    212    211    212            �           2604    16833    pension pension_id    DEFAULT     x   ALTER TABLE ONLY public.pension ALTER COLUMN pension_id SET DEFAULT nextval('public.pension_pension_id_seq'::regclass);
 A   ALTER TABLE public.pension ALTER COLUMN pension_id DROP DEFAULT;
       public          postgres    false    214    213    214            �           2604    16888    room room_id    DEFAULT     l   ALTER TABLE ONLY public.room ALTER COLUMN room_id SET DEFAULT nextval('public.room_room_id_seq'::regclass);
 ;   ALTER TABLE public.room ALTER COLUMN room_id DROP DEFAULT;
       public          postgres    false    219    218    219            �           2604    16819    user user_id    DEFAULT     n   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    209    210    210            0          0    16907    booking 
   TABLE DATA           �   COPY public.booking (booking_id, room_id, customer_name, customer_tc, customer_mail, customer_phone, start_date, finish_date, child_number, adult_number, total_price, customer_note) FROM stdin;
    public          postgres    false    221   �@       '          0    16823    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, city, region, address, email, phone, star, freeparking, freewifi, swimmingpool, gym, concierge, spa, roomservice) FROM stdin;
    public          postgres    false    212   B       )          0    16830    pension 
   TABLE DATA           Q   COPY public.pension (pension_id, hotel_id, pension_name, hotel_name) FROM stdin;
    public          postgres    false    214   �B       .          0    16885    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, pension_id, season_id, room_type, room_stock, bed_num, square_meters, tv, mini_bar, game_console, hotel_safe, projection, child_price, adult_price, room_price) FROM stdin;
    public          postgres    false    219   �C       +          0    16842    season 
   TABLE DATA           Z   COPY public.season (season_id, hotel_id, hotel_name, start_date, finish_date) FROM stdin;
    public          postgres    false    216   ZD       %          0    16816    user 
   TABLE DATA           R   COPY public."user" (user_id, user_username, user_password, user_role) FROM stdin;
    public          postgres    false    210    E       =           0    0    booking_booking_id_seq    SEQUENCE SET     E   SELECT pg_catalog.setval('public.booking_booking_id_seq', 22, true);
          public          postgres    false    220            >           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 5, true);
          public          postgres    false    211            ?           0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 7, true);
          public          postgres    false    213            @           0    0    room_room_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.room_room_id_seq', 7, true);
          public          postgres    false    218            A           0    0    season_season_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.season_season_id_seq', 1, true);
          public          postgres    false    215            B           0    0    season_season_id_seq1    SEQUENCE SET     D   SELECT pg_catalog.setval('public.season_season_id_seq1', 17, true);
          public          postgres    false    217            C           0    0    user_user_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.user_user_id_seq', 12, true);
          public          postgres    false    209            �           2606    16914    booking booking_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT booking_pkey PRIMARY KEY (booking_id);
 >   ALTER TABLE ONLY public.booking DROP CONSTRAINT booking_pkey;
       public            postgres    false    221            �           2606    16828    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    212            �           2606    16835    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    214            �           2606    16890    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    219            �           2606    16847    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    216            �           2606    16821    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    210            �           2606    16924    booking fk_room    FK CONSTRAINT     r   ALTER TABLE ONLY public.booking
    ADD CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES public.room(room_id);
 9   ALTER TABLE ONLY public.booking DROP CONSTRAINT fk_room;
       public          postgres    false    219    221    3472            �           2606    16836    pension pension_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 G   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_hotel_id_fkey;
       public          postgres    false    3466    212    214            �           2606    16891    room room_hotel_id_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 A   ALTER TABLE ONLY public.room DROP CONSTRAINT room_hotel_id_fkey;
       public          postgres    false    219    212    3466            �           2606    16896    room room_pension_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pension_id_fkey FOREIGN KEY (pension_id) REFERENCES public.pension(pension_id);
 C   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pension_id_fkey;
       public          postgres    false    214    3468    219            �           2606    16901    room room_season_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_season_id_fkey FOREIGN KEY (season_id) REFERENCES public.season(season_id);
 B   ALTER TABLE ONLY public.room DROP CONSTRAINT room_season_id_fkey;
       public          postgres    false    216    3470    219            �           2606    16848    season season_hotel_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_hotel_id_fkey FOREIGN KEY (hotel_id) REFERENCES public.hotel(hotel_id);
 E   ALTER TABLE ONLY public.season DROP CONSTRAINT season_hotel_id_fkey;
       public          postgres    false    216    3466    212            0   �   x�m�;n�0�g������xs��@%C�5D�e���+�h����|�I�W����q������
�	���}~^O�!,�k���#�M��� � �����x\�kΗ�UI��x�D ���鴸��������2HdM���}���Q�nH��:e���-�TV��h��/�NLKg4����˃��*kxz�*`�d�!'�r�,`Q1��S����S̼��߅ʸb�s�R�TEeb      '   �   x�m��j�0���S�	����e,��t��uv1�JCf���>}�i�1ld�-�ק��z\��~ac}�Z�<�cK������:{���}���T��Jg8�h2(��<��z�C
^ɓ��������:F���p���~F�P(?��9�PR{�	�J��-���P(�z��55��/��r�ϝ�&�3���E���m"��%4>����蘨H^���8�b��;�������      )   �   x�Uͱ�0����)|t��6 h�Ln:t ���Oot��!�3�'&c�����]��>���&Q ���m��R2�0�M�4P��\ݨ��]���W��o|D	���=���N8 1u��^Q�i��aY�c/�� �!1�      .   �   x�U��
�0�ϓ����6�*�T���ބ���?Φ�X��ݙo����]�桔���⋅e�
�«���?<��+�<N�{�Ekɦ��A(M".�����I��ٕ�5H!N\�8����X�W��A��5����GI֎�Ũ
�#���s��M��I���F 6�99�~H#<9      +   �   x�m�=�0Fg�\ �v~(;�1�d�P��v��qD����~�g0\c̻�kIOg�����ҳ�Y:@�N)�)�xb�*E}m�4.��b��K��#F=��e��w�l��������5�j�,�ӼԾh��32�c����C|      %   j   x�3�LN��,N�31M?�'/95���9]}|�#]]�9Sr3󠤣����%gIjq���1'�(�r��҂�"G�b��(�C#��܂����T�-F��� ��('     