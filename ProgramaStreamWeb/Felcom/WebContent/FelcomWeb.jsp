<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Felcom</title>
</head>
<body>
	<h1>Peliculas</h1>
	<form name="formaPeliculas"  method="post">
		<div class="row">
			<div class="col-sm-9">
			</div>
		</div>
		<div>
           <p>Reproductor VLC</p>
           <embed type="application/x-vlc-plugin" pluginspage="http://www.videolan.org" version="VideoLAN.VLCPlugin.2"  
             width="640"  height="480" id="vlc" loop="yes" autoplay="yes" target="rtsp://192.168.43.134:8554/JQ"></embed>
       </div>
	</form>
</body>
</html>