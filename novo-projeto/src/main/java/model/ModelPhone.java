package model;

public class ModelPhone {
	
	private short _id;
	private String _number;
	private String _type;
	private short _user_id;
	
	/* Construtores */
	
	public ModelPhone() {
		
	}
	
	public ModelPhone(short _id, String _number, String _type, short _user_id) {
		this._id = _id;
		this._number = _number;
		this._type = _type;
		this._user_id = _user_id;
	}
	
	/* Setters e Getters*/
	
	public short get_id() {
		return _id;
	}
	public void set_id(short _id) {
		this._id = _id;
	}
	public String get_number() {
		return _number;
	}
	public void set_number(String _number) {
		this._number = _number;
	}
	public String get_type() {
		return _type;
	}
	public void set_type(String _type) {
		this._type = _type;
	}
	public short get_user_id() {
		return _user_id;
	}
	public void set_user_id(short _user_id) {
		this._user_id = _user_id;
	}
	
	/* toString */

	@Override
	public String toString() {
		return "ModelPhone [_id=" + _id + ", _number=" + _number + ", _type=" + _type + ", _user_id=" + _user_id + "]";
	}
	
}