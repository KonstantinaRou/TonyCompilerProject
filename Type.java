
public  class Type
{

	
	public static final String INT="int";
	public static final String BOOL="bool";
	public static final String CHAR="char";
	public static final String LIST="list";
	public static final String ARRAY="[]";
	public static final String NIL="nil";
	public static final String VOID="void";
	

	
	private String primaryType ;
	private Type secondaryType;
	
	
	public Type (String type)
	{
		this.primaryType=type;
	}
	
	public Type (String ptype , Type stype)
	{
		this.primaryType=ptype;
		this.secondaryType=stype;
	}
	
	Type getInnerType(){return secondaryType;}
	String getType(){return primaryType;}
	
	
	@Override
	public boolean equals(Object arg0) {
		Type arg;
		
		if (! (arg0 instanceof Type)) 
			return false;
		else 
		{
			arg=(Type)arg0;
			if(this.isBasicType()!=arg.isBasicType())
				return false;
			if(this.isBasicType()&&arg.isBasicType())
			{
				return this.primaryType.equals(arg.primaryType);
			}
			else if(!(this.isBasicType() || arg.isBasicType()))
			{
				if(this.isVoid()&& arg.isVoid())
					return true;
				
				if(this.isList())
				{
					if(arg.isNil())
						return true;
					if(!arg.isList())
						return false;
					return (this.secondaryType.equals(arg.secondaryType));
				}
				else if(this.isNil()){return arg.isList();}
				else if(this.isArray() && arg.isArray())
				{
					return this.secondaryType.equals(arg.secondaryType);
				}
				
			}
		}
		return false;
		
	}
	
	public boolean isInt(){return this.primaryType.equals(INT);}
	public boolean isBool(){return this.primaryType.equals(BOOL);}
	public boolean isChar(){return this.primaryType.equals(CHAR);}
	public boolean isList(){return this.primaryType.equals(LIST);}
	public boolean isArray(){return this.primaryType.equals(ARRAY);}
	public boolean isNil(){return this.primaryType.equals(NIL);}
	public boolean isVoid(){return this.primaryType.equals(VOID);}
	
	public static Type VoidType(){return new Type(Type.VOID);}
	public static Type IntType(){return new Type(Type.INT);}
	public static Type BoolType(){return new Type(Type.BOOL);}
	public static Type CharType(){return new Type(Type.CHAR);}
	public static Type ListType(){return new Type(Type.LIST);}
	public static Type ArrayType(){return new Type(Type.ARRAY);}
	public static Type NilType(){return new Type(Type.NIL);}
	
	
	public boolean isBasicType()
	{
		return (primaryType.equals(Type.INT)||primaryType.equals(Type.BOOL)||primaryType.equals(Type.CHAR));
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(secondaryType==null)
		return primaryType;
		return primaryType+secondaryType.toString();
	}
	

	
	
}