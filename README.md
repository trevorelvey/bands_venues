# Bands & Venues

#### Webpage for listing bands and the venues they've played at, 3/4/16

#### By Trevor Elvey

## Description

##### This website will allow the user to add bands and venues and list what venues bands have played.

## Setup/Installation Requirements

#### Run webpage with gradle.

#### In PSQL:
#### CREATE DATABASE bands_venues;
#### CREATE TABLE bands (id serial PRIMARY KEY, band_name varchar);
#### CREATE TABLE venues (id serial PRIMARY KEY, venue_name varchar);
#### CREATE TABLE bands_venues (id serial PRIMARY KEY, band_id int, venue_id int);

## Known Bugs

##### No bugs known.

## Support and contact details

##### For contact email trevor.elvey@gmail.com

## Technologies Used

##### HTML, CSS, Bootstrap, Java, Gradle, SQL

### License

##### Copyright (c) 2016 Trevor Elvey

##### Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

##### The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

##### THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
