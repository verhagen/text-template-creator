# Text Template Creator

Creates text files, with predefined outlines. Think of a text file that contains:

- logbook entries per day, in Markdown format
- time sheet entries per day, in csv format


## Available Creators

### Logbook

- Creates a logbook for a given month in a year
- For each day one entry
- In Markdown format

```
# Logbook 2020 January

## 2020.01.31 Friday

## 2020.01.30 Thursday

## 2020.01.29 Wednesday

...

## 2020.01.01 Wednesday (New Year's Day)

```


### Time Sheet per Month

- Creates a time sheet for a given month in a year
- For each day one or more rows
- In csv format

```
# Time Sheet 2020 January

year; week; date; day; hours; activity
2020; 05; 2020.01.31; Fri; ;
                         ; ;
2020; 05; 2020.01.30; Thu; ;
                         ; ;
2020; 05; 2020.01.29; Wed; ;
                         ; ;
2020; 05; 2020.01.28; Tue; ;
                         ; ;
2020; 05; 2020.01.27; Mon; ;
                         ; ;

...

year; week; date; day; hours; activity
2020; 01; 2020.01.03; Fri; ;
                         ; ;
2020; 01; 2020.01.02; Thu; ;
                         ; ;
2020; 01; 2020.01.01; Wed; ; (New Year's Day)
                         ; ;
```


### Time Sheet per Year

- Creates a time sheet for a given year
- For each week one rows, containing all days of the week
- In csv format

```
week   ; start - end            ; m; t; w; t; f;
2020.53; 2020.12.28 - 2021.01.03; ; ; ; ; ;
2020.52; 2020.12.21 - 2020.12.27; ; ; ; ; ;
2020.51; 2020.12.14 - 2020.12.20; ; ; ; ; ;

...

2020.03; 2020.01.13 - 2020.01.19; ; ; ; ; ;
2020.02; 2020.01.06 - 2020.01.12; ; ; ; ; ;
2020.01; 2019.12.30 - 2020.01.05; ; ; ; ; ;
```
