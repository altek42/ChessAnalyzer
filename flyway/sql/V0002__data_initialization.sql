INSERT INTO public."user" (username,"password",chess_id)
	VALUES ('Altek42','$2a$12$rsh5iZz28Rv5tqrsJ8zNgO/Z6tk6dIbmjOLKYm4k0cENKoyhRKoVm','d15b424c-2726-11eb-9577-df5e0a04388a'::uuid);

INSERT INTO public.properties ("name",value) VALUES 
	('API_CHESSBOARD_URL','http://localhost:3000'),
	('API_CHESSCOM_URL','http://localhost:7001/pub');

