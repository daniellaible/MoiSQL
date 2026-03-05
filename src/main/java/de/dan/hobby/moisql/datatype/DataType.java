package de.dan.hobby.moisql.datatype;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * These are the datatypes we will support
 * BIGINT   -   64-bit signed
 * INT      -   32-bit signed
 * SMALLINT -   16-bit signed
 * DECIMAL  -   32-bit signed
 * FLOAT    -   64-bit signed
 * DATE     -   64-bit
 * TIME     -   64-bit
 * DATETIME -   64-bit
 * VARCHAR  -   max 255 characters ascii
 * TEXT     -   max 8000 characters ascii
 * BINARY   -   ????
 * BOOL     -    1-bit ?
 *
 * Implemented Datatypes:
 *
 */
public enum DataType {
  BIGINT,
  INT,
  SMALLINT,
  TINYINT,
  DECIMAL,
  FLOAT,
  VARCHAR,
  TEXT,
  DATE,
  TIME,
  DATETIME,
  BINARY,
  BOOL
}
