# Save-As-Scala-Api is a Scala api which is used to save a copy of reports (basically excel files) having many primary keys which are foreign keys in some other excel files.
Thus in order to save as we need to change the primary keys with newly generated UUID primary keys in all the excel files.
The main challenging task here was since a primary key in one file acts as foreign key in multiple files, thus to maintain consistency in all excel files the occurence was supposed to change everywhere.

In input.txt the input was given like "name of the excel file","Primary key column" 
