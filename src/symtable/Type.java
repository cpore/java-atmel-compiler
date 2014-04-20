package symtable;

import exceptions.*;

import java.util.*;

public class Type{
	
  public static final Type BOOL = new Type();
  public static final Type INT = new Type();
  public static final Type TONE = new Type();
  public static final Type BYTE = new Type();
  public static final Type COLOR = new Type();
  public static final Type BUTTON = new Type();
  public static final Type VOID = new Type();
  private static final HashMap<String, Type> classTypes = new HashMap<String, Type>();
  private String className;
  
  private Type(){

  }
  
  private Type(String className){
	  this.className = className;
  }

  public static Type getClassType(String className){
	  return classTypes.get("class_" + className);
  }
  
  public String getClassName(){
	  return className.substring(6);
  }
  
  public static void setClassType(String className){
	 Type classType = new Type("class_" + className);
	 classTypes.put("class_" + className, classType);
  }
    
/*
*/

  public String toString(){
	  
    if(this == INT)
    {
      return "INT";
    }
    
    if(this == TONE)
    {
      return "TONE";
    }

    if(this == BOOL)
    {
      return "BOOL";
    }

    if(this == BYTE)
    {
      return "BYTE";
    }

    if(this == COLOR)
    {
      return "COLOR";
    }

    if(this == BUTTON)
    {
      return "BUTTON";
    }
    
    if(this == VOID)
    {
      return "VOID";
    }

    return className;
/*
*/
    //return "MAINCLASS;";
  }
  
  public int getAVRTypeSize() {
      if(this == INT) { return 2; }
      if(this == TONE) { return 2; }
      if(this == BOOL) { return 1; }
      if(this == BYTE) { return 1; }
      if(this == COLOR) { return 1; }
      if(this == BUTTON) { return 1; }
      if(this == VOID) { return 0; }

      return 2; // class references are 2 bytes
  }

    
/*  
*/

}
