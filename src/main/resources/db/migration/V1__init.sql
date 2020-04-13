create table if not exists userAccounts
(
	id bigint unsigned auto_increment
		primary key,
	password char(60) not null,
	email varchar(150) not null,
	firstName varchar(150) null,
	lastName varchar(150) null,
	status enum('ACTIVE', 'SUSPENDED', 'DISABLED') not null,
	lastLoginAt datetime null,
	createdAt datetime not null,
	createdBy bigint unsigned not null,
	updatedAt datetime null,
	updatedBy bigint unsigned not null,
	archivedAt datetime null,
	archivedBy bigint unsigned null,
	constraint email
		unique (email),
	constraint FK_userAccounts_archivedBy
		foreign key (archivedBy) references userAccounts (id)
			on update cascade,
	constraint FK_userAccounts_createdBy
		foreign key (createdBy) references userAccounts (id)
			on update cascade,
	constraint FK_userAccounts_updatedBy
		foreign key (updatedBy) references userAccounts (id)
			on update cascade
);

create index archivedAt
	on userAccounts (archivedAt);

create index firstName
	on userAccounts (firstName);

create index lastName
	on userAccounts (lastName);

create index status
	on userAccounts (status);


create table userPermissions
(
	id bigint unsigned auto_increment
		primary key,
	name varchar(150) not null,
	parentId bigint unsigned null,
	createdAt datetime not null,
	createdBy bigint unsigned not null,
	updatedAt datetime null,
	updatedBy bigint unsigned not null,
	archivedAt datetime null,
	archivedBy bigint unsigned null,
	constraint name
		unique (name),
	constraint FK_userPermissions_archivedBy
		foreign key (archivedBy) references userAccounts (id)
			on update cascade,
	constraint FK_userPermissions_createdBy
		foreign key (createdBy) references userAccounts (id)
			on update cascade,
	constraint FK_userPermissions_parentId
		foreign key (parentId) references userPermissions (id)
			on update cascade,
	constraint FK_userPermissions_updatedBy
		foreign key (updatedBy) references userAccounts (id)
			on update cascade
);

create index archivedAt
	on userPermissions (archivedAt);

create table userRoles
(
	id bigint unsigned auto_increment
		primary key,
	name varchar(150) not null,
	parentId bigint unsigned null,
	isAlmighty tinyint(1) default 0 null,
	createdAt datetime not null,
	createdBy bigint unsigned not null,
	updatedAt datetime null,
	updatedBy bigint unsigned not null,
	archivedAt datetime null,
	archivedBy bigint unsigned null,
	constraint name
		unique (name),
	constraint FK_userRoles_archivedBy
		foreign key (archivedBy) references userAccounts (id)
			on update cascade,
	constraint FK_userRoles_createdBy
		foreign key (createdBy) references userAccounts (id)
			on update cascade,
	constraint FK_userRoles_parentId
		foreign key (parentId) references userRoles (id)
			on update cascade,
	constraint FK_userRoles_updatedBy
		foreign key (updatedBy) references userAccounts (id)
			on update cascade
);

create index archivedAt
	on userRoles (archivedAt);

create index isAlmighty
	on userRoles (isAlmighty);


create table userRolesPermissions
(
	userRoleId bigint unsigned not null,
	userPermissionId bigint unsigned not null,
	primary key (userRoleId, userPermissionId),
	constraint FK_userRolesPermissions_userPermissionId
		foreign key (userPermissionId) references userPermissions (id)
			on update cascade,
	constraint FK_userRolesPermissions_userRoleId
		foreign key (userRoleId) references userRoles (id)
			on update cascade
);


create table userAccountsRoles
(
	userAccountId bigint unsigned not null,
	userRoleId bigint unsigned not null,
	primary key (userAccountId, userRoleId),
	constraint FK_userAccountsRoles_userAccountId
		foreign key (userAccountId) references userAccounts (id)
			on update cascade,
	constraint FK_userAccountsRoles_userRoleId
		foreign key (userRoleId) references userRoles (id)
			on update cascade
);

create table cosco_edi_can_dev.userAccountsPermissions
(
	userAccountId bigint unsigned not null,
	userPermissionId bigint unsigned not null,
	primary key (userAccountId, userPermissionId),
	constraint FK_userAccountsPermissions_userAccountId
		foreign key (userAccountId) references cosco_edi_can_dev.userAccounts (id)
			on update cascade,
	constraint FK_userAccountsPermissions_userPermissionId
		foreign key (userPermissionId) references cosco_edi_can_dev.userPermissions (id)
			on update cascade
);

INSERT INTO userAccounts(id, status, nickname, password, email, firstName, lastName, createdAt, createdBy, updatedAt, updatedBy) VALUES
    (1, 'ACTIVE', 'rainmore', '$2a$10$evcItoK3Jj13m74WOAd05u7Gw4MGYVTYToytvyu.IG9xP57SSD7au', 'rainmore24@gmail.com', 'Admin', '', NOW(), 1, NOW(), 1),
    (2, 'ACTIVE', 'admin', '$2a$10$evcItoK3Jj13m74WOAd05u7Gw4MGYVTYToytvyu.IG9xP57SSD7au', 'cliff@cosco.com.au', 'Cliff', 'Sun', NOW(), 1, NOW(), 1),
    (3, 'ACTIVE', 'demo', '$2a$10$evcItoK3Jj13m74WOAd05u7Gw4MGYVTYToytvyu.IG9xP57SSD7au', 'demo@test.com',  'Demo', 'Test', NOW(), 1, NOW(), 1)
;


INSERT INTO userRoles(id, name, parentId, isAlmighty, createdAt, createdBy, updatedAt, updatedBy) VALUES
    (1, 'System', null, true, NOW(), 1, NOW(), 1),
    (2, 'Admin', null, false, NOW(), 1, NOW(), 1),
    (3, 'User', null, false, NOW(), 1, NOW(), 1)
;

INSERT INTO userAccountsRoles (userAccountId, userRoleId) VALUES
    (1, 1), (2, 2), (3, 3);

INSERT INTO userPermissions(id, name, parentId, createdAt, createdBy, updatedAt, updatedBy) VALUES
    (1, 'users.accounts.admin', null, NOW(), 1, NOW(), 1),
    (2, 'users.accounts.create', 1, NOW(), 1, NOW(), 1),
    (3, 'users.accounts.read', 1, NOW(), 1, NOW(), 1),
    (4, 'users.accounts.update', 1, NOW(), 1, NOW(), 1),
    (5, 'users.accounts.archive', 1, NOW(), 1, NOW(), 1),
    (6, 'users.accounts.delete', 1, NOW(), 1, NOW(), 1),
    (7, 'users.accounts.profile', 1, NOW(), 1, NOW(), 1),
    (8, 'users.roles.admin', null, NOW(), 1, NOW(), 1),
    (9, 'users.roles.create', 8, NOW(), 1, NOW(), 1),
    (10, 'users.roles.read', 8, NOW(), 1, NOW(), 1),
    (11, 'users.roles.update', 8, NOW(), 1, NOW(), 1),
    (12, 'users.roles.archive', 8, NOW(), 1, NOW(), 1),
    (13, 'users.roles.delete', 8, NOW(), 1, NOW(), 1),
    (14, 'users.roles.profile', 8, NOW(), 1, NOW(), 1),
    (15, 'users.permissions.admin', null, NOW(), 1, NOW(), 1)
;

INSERT INTO userRolesPermissions (userRoleId, userPermissionId) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 14), (1, 15),
    (2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 7), (2, 14),
    (3, 3), (3, 4), (3, 7), (3, 14)
;

create table userLoginTokens
(
	series varchar(64) not null primary key,
	userAccountId bigint unsigned not null,
	token varchar(64) not null,
	lastUsedAt datetime not null,
	constraint FK_userLoginTokens_userAccountId
		foreign key (userAccountId) references userAccounts (id)
			on update cascade
);

create index token
	on userLoginTokens (token);

create index lastUsedAt
	on userLoginTokens (lastUsedAt);

create table userForgetPasswordRequests
(
	id binary(36) not null
		primary key,
	userAccountId bigint unsigned not null,
	createdAt datetime not null,
	constraint FK_userForgetPasswordRequests_userAccountId
		foreign key (userAccountId) references userAccounts (id)
			on update cascade on delete cascade
);


create index createdAt
	on userForgetPasswordRequests (createdAt);


create table systemProperties
(
	name varchar(256) not null primary key,
	data text not null
);


INSERT INTO systemProperties VALUES
('rainmore.cms.author.name','\"rainmore\"'),
('rainmore.cms.author.url','\"https://rainmore.com.au\"'),
('rainmore.cms.document.url','null'),
('rainmore.cms.mail.from.email','null'),
('rainmore.cms.mail.replyTo.email','null'),
('rainmore.cms.mail.smtp.host ','null'),
('rainmore.cms.mail.smtp.password ','null'),
('rainmore.cms.mail.smtp.port ','null'),
('rainmore.cms.mail.smtp.username ','null'),
('rainmore.cms.name','\"Rainmore CMS\"'),
('rainmore.cms.release','\"1.0\"');

