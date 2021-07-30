package model;

public class ModelAddress {
	
	private short _id;
	private short _number;
	private short _user_id;
	private String _country;
	private String _state;
	private String _city;
	private String _street;
	
	/* Construtores*/
	
	public ModelAddress() {
		
	}
	
	public ModelAddress(short _number, short _user_id, String _country, String _state, String _city, String _street) {
		this._number = _number;
		this._user_id = _user_id;
		this._country = _country;
		this._state = _state;
		this._city = _city;
		this._street = _street;
	}
	
	/* Gets e Sets */ 

	public short get_id() {
		return _id;
	}

	public void set_id(short _id) {
		this._id = _id;
	}

	public short get_number() {
		return _number;
	}

	public void set_number(short _number) {
		this._number = _number;
	}

	public short get_user_id() {
		return _user_id;
	}

	public void set_user_id(short _user_id) {
		this._user_id = _user_id;
	}

	public String get_country() {
		return _country;
	}

	public void set_country(String _country) {
		this._country = _country;
	}

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}

	public String get_street() {
		return _street;
	}

	public void set_street(String _street) {
		this._street = _street;
	}
	
	/* toString */

	@Override
	public String toString() {
		return "ModelAddress [_id=" + _id + ", _number=" + _number + ", _user_id=" + _user_id + ", _country=" + _country
				+ ", _state=" + _state + ", _city=" + _city + ", _street=" + _street + "]";
	}

}
