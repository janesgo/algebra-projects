-- Java Project SQL Script
-- Goran Janeš

CREATE DATABASE ProjectJava
GO
USE ProjectJava
GO

-- %%%%%%%% ROLES %%%%%%%% --

CREATE TABLE Roles
(
	IDRole INT PRIMARY KEY IDENTITY,
	Name NVARCHAR(32) NOT NULL
)
GO

INSERT INTO Roles VALUES ('Administrator')
INSERT INTO Roles VALUES ('User')
GO

-- %%%%%%%% USERS %%%%%%%% --

CREATE TABLE Users
(
	IDUser INT PRIMARY KEY IDENTITY,
	Username NVARCHAR(32) NOT NULL,
	Password NVARCHAR(8) NOT NULL,
	RoleID INT NOT NULL FOREIGN KEY REFERENCES Roles(IDRole)
)
GO

INSERT INTO Users VALUES ('admin', 'admin000', 1)
GO

CREATE PROCEDURE Users_Create
	@Username NVARCHAR(32),
	@Password NVARCHAR(8),
	@RoleID INT,
	@Id INT OUTPUT
AS
BEGIN
	SET NOCOUNT ON
	IF EXISTS (SELECT IDUser FROM Users WHERE Username = @Username)
		SET @Id = -1
	ELSE
	BEGIN
		INSERT INTO Users VALUES(@Username, @Password, @RoleID)
		SET @Id = SCOPE_IDENTITY()
	END
END
GO

CREATE PROCEDURE Users_Select
	@Username NVARCHAR(32),
	@Password NVARCHAR(8)
AS
BEGIN
	SET NOCOUNT ON
	SELECT * FROM Users WHERE Username = @Username and Password = @Password
END
GO

-- %%%%%%%% POSITIONS %%%%%%%% --

CREATE TABLE Positions
(
	IDPosition INT PRIMARY KEY IDENTITY,
	Name NVARCHAR(32) NOT NULL
)
GO

INSERT INTO Positions VALUES ('Actor')
INSERT INTO Positions VALUES ('Director')
GO

CREATE PROCEDURE Positions_Select
	@Id INT
AS
BEGIN
	SELECT * FROM Positions WHERE IDPosition = @Id
END
GO

CREATE PROCEDURE Positions_SelectId
	@Name NVARCHAR(32),
	@Id INT OUTPUT
AS
BEGIN
	SELECT @Id = IDPosition FROM Positions WHERE Name = @Name
END
GO

-- %%%%%%%% GENRES %%%%%%%% --

CREATE TABLE Genres
(
	IDGenre INT PRIMARY KEY IDENTITY,
	Name NVARCHAR(100) NOT NULL,
)
GO

CREATE PROCEDURE Genres_Create
	@Name NVARCHAR(100),
	@Id INT OUTPUT
AS
BEGIN
	INSERT INTO Genres VALUES(@Name)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE Genres_Update
	@Name NVARCHAR(100),
	@IdGenre INT
AS
BEGIN
	UPDATE Genres SET Name = @Name WHERE IDGenre = @IdGenre
END
GO

CREATE PROCEDURE Genres_Delete
	@IdGenre INT
AS
BEGIN
	DELETE FROM Genres WHERE IDGenre = @IdGenre
END
GO

CREATE PROCEDURE Genres_Select
	@IdGenre INT
AS
BEGIN
	SELECT * FROM Genres WHERE IDGenre = @IdGenre
END
GO

CREATE PROCEDURE Genres_SelectAll
AS
BEGIN
	SELECT * FROM Genres
END
GO

CREATE PROCEDURE Genres_SelectId
	@Name NVARCHAR(200),
	@Id INT OUTPUT
AS
BEGIN
	SELECT @Id = IDGenre FROM Genres WHERE Name = @Name
END
GO

-- %%%%%%%% PEOPLE %%%%%%%% --

CREATE TABLE People
(
	IDPerson INT PRIMARY KEY IDENTITY,
	Name NVARCHAR(200) NOT NULL
)
GO

CREATE PROCEDURE People_Create
	@Name NVARCHAR(200),
	@Id INT OUTPUT
AS
BEGIN
	INSERT INTO People VALUES(@Name)
	SET @Id = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE People_Update
	@Name NVARCHAR(200),
	@Id INT
AS
BEGIN
	UPDATE People SET Name = @Name WHERE IDPerson = @Id
END
GO

CREATE PROCEDURE People_Delete
	@Id INT
AS
BEGIN
	DELETE FROM People WHERE IDPerson = @Id
END
GO

CREATE PROCEDURE People_Select
	@Id INT
AS
BEGIN
	SELECT * FROM People WHERE IDPerson = @Id
END
GO

CREATE PROCEDURE People_SelectAll
AS
BEGIN
	SELECT * FROM People
END
GO

CREATE PROCEDURE People_SelectId
	@Name NVARCHAR(200),
	@Id INT OUTPUT
AS
BEGIN
	SELECT @Id = IDPerson FROM People WHERE Name = @Name
END
GO

-- %%%%%%%% MOVIES %%%%%%%% --

CREATE TABLE Movies
(
	IDMovie INT PRIMARY KEY IDENTITY,
	Title NVARCHAR(300) NOT NULL,
	PublishedDate NVARCHAR(64) NOT NULL,
	Description NVARCHAR(3000),
	OriginalTitle NVARCHAR(300) NOT NULL,
	Duration INT NOT NULL,
	PosterPath NVARCHAR(400) NOT NULL,
	Link NVARCHAR(400) NOT NULL,
	ScreeningStartsDate NVARCHAR(32) NOT NULL
)
GO

CREATE PROCEDURE Movies_Create
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(64),
	@Description NVARCHAR(3000),
	@OriginalTitle NVARCHAR(300),
	@Duration INT,
	@PosterPath NVARCHAR(400),
	@Link NVARCHAR(400),
	@ScreeningStartsDate NVARCHAR(32),
	@IdMovie INT OUTPUT
AS
BEGIN
	INSERT INTO Movies VALUES(@Title, @PublishedDate, @Description, @OriginalTitle, @Duration, @PosterPath, @Link, @ScreeningStartsDate)
	SET @IdMovie = SCOPE_IDENTITY()
END
GO

CREATE PROCEDURE Movies_Update
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(64),
	@Description NVARCHAR(3000),
	@OriginalTitle NVARCHAR(300),
	@Duration INT,
	@PosterPath NVARCHAR(400),
	@Link NVARCHAR(400),
	@ScreeningStartsDate NVARCHAR(32),
	@IdMovie INT
AS
BEGIN
	UPDATE Movies SET
		Title = @Title,
		PublishedDate = @PublishedDate,
		Description = @Description,
		OriginalTitle = @OriginalTitle,
		Duration = @Duration,
		PosterPath = @PosterPath,
		ScreeningStartsDate = @ScreeningStartsDate
	WHERE IDMovie = @IdMovie
END
GO

CREATE PROCEDURE Movies_Delete
	@IdMovie INT
AS
BEGIN
	DELETE FROM MovieGenre WHERE MovieID = @IdMovie
	DELETE FROM PersonPosition WHERE MovieID = @IdMovie
	DELETE FROM Movies WHERE IDMovie = @IdMovie
END
GO

CREATE PROCEDURE Movies_Select
	@IdMovie INT
AS
BEGIN
	SELECT * FROM Movies WHERE IDMovie = @IdMovie
END
GO

CREATE PROCEDURE Movies_SelectAll
AS
BEGIN
	SELECT * FROM Movies
END
GO

CREATE PROCEDURE Movies_SelectId
	@Title NVARCHAR(300),
	@PublishedDate NVARCHAR(64),
	@Description NVARCHAR(3000),
	@OriginalTitle NVARCHAR(300),
	@Duration INT,
	@Id INT OUTPUT
AS
BEGIN
	SELECT @Id = IDMovie FROM Movies WHERE
		Title = @Title AND
		PublishedDate = @PublishedDate AND
		Description = @Description AND
		OriginalTitle = @OriginalTitle AND
		Duration = @Duration
END
GO

---- %%%%%%%% MOVIES - POSITIONS %%%%%%%% --

CREATE TABLE PersonPosition
(
	PersonID INT NOT NULL FOREIGN KEY REFERENCES People(IDPerson),
	MovieID INT NOT NULL FOREIGN KEY REFERENCES Movies(IDMovie),
	PositionID INT NOT NULL FOREIGN KEY REFERENCES Positions(IDPosition)
	CONSTRAINT PK_PersonPosition PRIMARY KEY (PersonID, MovieID, PositionID)
)
GO

CREATE PROCEDURE Movies_AddPerson
	@IdPerson INT,
	@IdMovie INT,
	@IdPosition INT
AS
BEGIN
	INSERT INTO PersonPosition VALUES(@IdPerson, @IdMovie, @IdPosition)
END
GO

CREATE PROCEDURE Movies_RemovePerson
	@IdPerson INT,
	@IdMovie INT,
	@IdPosition INT
AS
BEGIN
	DELETE FROM PersonPosition WHERE PersonID = @IdPerson AND MovieID = @IdMovie AND PositionID = @IdPosition
END
GO

CREATE PROCEDURE Movies_SelectPeople
	@IdMovie INT,
	@IdPosition INT
AS
BEGIN
	SELECT p.* FROM PersonPosition pp JOIN People p on p.IDPerson=pp.PersonID WHERE MovieID = @IdMovie and pp.PositionID = @IdPosition
END
GO

---- %%%%%%%% MOVIES - GENRES %%%%%%%% --

CREATE TABLE MovieGenre
(
	MovieID INT NOT NULL FOREIGN KEY REFERENCES Movies(IDMovie),
	GenreID INT NOT NULL FOREIGN KEY REFERENCES Genres(IDGenre),
	CONSTRAINT PK_MovieGenre PRIMARY KEY (MovieID, GenreID)
)
GO

CREATE PROCEDURE Movies_AddGenre
	@IdMovie INT,
	@IdGenre INT
AS
BEGIN
	INSERT INTO MovieGenre VALUES(@IdMovie, @IdGenre)
END
GO

CREATE PROCEDURE Movies_RemoveGenre
	@IdMovie INT,
	@IdGenre INT
AS
BEGIN
	DELETE FROM MovieGenre WHERE MovieID = @IdMovie and GenreID = @IdGenre
END
GO

CREATE PROCEDURE Movies_SelectGenres
	@IdMovie INT
AS
BEGIN
	select g.* from MovieGenre mg join Genres g on g.IDGenre=mg.GenreID where MovieID = @IdMovie
END
GO

---- %%%%%%%% CLEAR DATABASE %%%%%%%% --

CREATE PROCEDURE Clear_Database
AS
BEGIN
	DELETE FROM PersonPosition
	DELETE FROM MovieGenre
	DELETE FROM People
	DELETE FROM Genres
	DELETE FROM Movies
END
GO
