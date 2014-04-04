package br.com.ibnetwork.xingu.utils.type.impl;

import br.com.ibnetwork.xingu.utils.type.ObjectType.Type;

public class BooleanTypeHandler
	extends TypeHandlerSupport
{
	public BooleanTypeHandler()
	{
		super(Boolean.class, "bool", Type.PRIMITIVE);
	}

	@Override
	public Object toObject(String value)
	{
		return Boolean.valueOf(value);
	}
	
	@Override
	public String toString(Object obj)
	{
		Boolean myBoolean = Boolean.class.cast(obj);
		return String.valueOf(myBoolean);
	}
}