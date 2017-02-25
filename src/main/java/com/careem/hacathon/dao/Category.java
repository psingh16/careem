package com.careem.hacathon.dao;

public class Category {
enum Type
{
SOLID,LIQUID,FRAGILE,INFLAMMABLE	
}
private Type itemtype;
public Type getItemtype() {
	return itemtype;
}
public void setItemtype(Type itemtype) {
	this.itemtype = itemtype;
	
}
@Override
public String toString() {
	return "Category [itemtype=" + itemtype + "]";
}

}
