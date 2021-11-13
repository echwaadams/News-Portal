**Wildlife Tracker**

REST API for querying and retrieving scoped news and information.

**Author**

Adams Echwa

**Setup/Installation Requirements**

.Fork this repo

. Clone the repo

. Open in terminal

.Navigate to appropriate directory using the cd command

. Type in the command git clone and paste the url of the cloned repo and press enter.

**Setup Requirements for Database**

- create database newsportal;
- CREATE TABLE users (id serial PRIMARY KEY, name varchar, position varchar, role varchar, departmentId int);
- CREATE TABLE departments (id serial PRIMARY KEY, name varchar, description varchar);
- CREATE TABLE news (id serial PRIMARY KEY, userId int, content varchar, postdate timestamp, departmentId int, type varchar);
- CREATE DATABASE newsportal_test WITH TEMPLATE newsportal;

**In order to run locally**

. Go to DB.class in main/java folder and make necessary changes.

. Go to DatabaseRule in test/java folder and make necessary changes.

**Technologies Used**

. Java

. CSS

. HBS

**Support and contact details**

for any issues and contributions you can reach me at: adamsechwa18@gmail.com

**License**

This project is licensed under the terms of the MIT license . Copyright (c) 2020 Adams Echwa