create table if not exists products (id bigserial primary key, title varchar(255) not null, price integer);

insert into products (title, price) values
                                        ('apple', 10),
                                        ('lemon', 10),
                                        ('water', 15),
                                        ('bread', 15),
                                        ('cheese', 50),
                                        ('milk', 20),
                                        ('banana', 15),
                                        ('cake', 50),
                                        ('chocolat', 30),
                                        ('fish', 30),
                                        ('meat', 100),
                                        ('drink', 10),
                                        ('wow', 50),
                                        ('starcraft', 50),
                                        ('dark souls 1', 40),
                                        ('dark souls 2', 50),
                                        ('dark souls 3', 50),
                                        ('demon souls', 50),
                                        ('bloodborne', 50),
                                        ('elden ring', 50);

