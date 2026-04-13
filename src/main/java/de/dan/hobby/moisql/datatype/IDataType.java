package de.dan.hobby.moisql.datatype;

import de.dan.hobby.moisql.datatype.text.VarChar;

/**
 * @author Daniel Laible
 * @since 0.0.2
 *
 * This is the generic interface of all different kinds of datatypes that are used
 * in MoiSql
 */
public interface IDataType<E> {

  <E> E getValue();

  DataType getDataType();

  byte[] toByteArray();

  <E> E fromByteArray(byte[] bytes);
}
