CREATE TABLE user_accounts (
    id             varchar(191) NOT NULL,
    name           varchar(191) NOT NULL,
    email          varchar(191) NOT NULL,
    sex            enum('man', 'woman') NOT NULL,
    created_at     timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    updated_at     timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE tweets (
    id             varchar(191) NOT NULL,
    content        text         NOT NULL,
    user_id        varchar(191) NOT NULL,
    created_at     timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    PRIMARY KEY (id),
    FOREIGN KEY (user_id)  REFERENCES user_accounts(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
