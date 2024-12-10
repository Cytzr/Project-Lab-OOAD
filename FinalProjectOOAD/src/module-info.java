module FinalProjectOOAD {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.base;
	requires java.sql;
	
	opens admin;
	opens model;
	opens hybrid_model;
	opens seller;
	opens user;
	opens components;
	opens main;
	opens controller;
	opens utilities;
}