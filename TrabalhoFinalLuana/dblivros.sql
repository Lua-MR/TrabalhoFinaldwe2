CREATE DATABASE livros;
USE livros;

-- Tabela para Usuários
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Tabela para Livros
CREATE TABLE book (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    pages INT NOT NULL
);

-- Tabela para Leituras com Exclusão em Cascata
CREATE TABLE reading (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    status VARCHAR(255) NOT NULL,
    reading_time DATETIME NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES book(id) ON DELETE CASCADE
);

-- Inserir Usuário Padrão
INSERT INTO user (name, email, password) VALUES 
    ('web', 'web@example.com', '$2a$10$D4kERIAQ7ESpH6uefy5ZluIohbBFDs6K3SyODdU5wATf6L0ULh7t2'); -- senha: 123

-- Inserir Livros Iniciais
INSERT INTO book (title, author, pages) VALUES 
    ('Livro de Teste 1', 'Autor 1', 200),
    ('Livro de Teste 2', 'Autor 2', 150),
    ('Livro de Teste 3', 'Autor 3', 300);

-- Inserir Leituras Iniciais
INSERT INTO reading (user_id, book_id, status, reading_time) VALUES 
(1, 1, 'Iniciado', '2003-02-11'), 
(1, 2, 'Concluído', '2003-02-11');

-- Inserir Novo Livro e Leitura
INSERT INTO book (id, title, author, pages) VALUES 
(99, 'Novo Livro', 'Novo Autor', 250);
INSERT INTO reading (user_id, book_id, status, reading_time) VALUES 
(1, 99, 'Em Progresso', '2024-08-22'); -- Adicione a leitura com status e data desejados

-- Verificar o conteúdo das tabelas
SELECT * FROM book;
SELECT * FROM reading;
