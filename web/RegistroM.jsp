<%-- 
    Document   : RegistroM
    Created on : 10/11/2016, 10:20:54 AM
    Author     : pcc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content ="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/estilos.css">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minium-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <!-- boton responsi-->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="index.jsp" class="navbar-brand" >CostosPerceptio</a>
                </div>

                <div class="collapse navbar-collapse " id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Inicio<span class="sr-only"></span></a></li>
                        <li><a href="uploadExcel.jsp">Subir Archivo</a></li>
                        <li><a href="Administracion.jsp">Administracion</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <br>
        <br>
        <br>
        <div class="container"> <!-- todos los textarea tipo input select llevan la clase form-control -->
            <form method="post" action="registrar.do" class="container" target="pp">
                <h1 class="title">Registro empleados</h1>
                <div class="form-group"> <!--esta clase sirve para dar un espacion entre label-->
                    <label for="identificacion">Identificacion</label>
                    <input class="form-control"name="identificacion" id="identificacion" type="number" placeholder="Solo numeros"required="true">
                </div>
                <div class="form-group">
                    <label for="nombre">Nombres</label>
                    <input class="form-control"id="nombre" name="nombre"type="text" placeholder="Nombres y apellidos completos" required="true">
                    <br>
                    <div class="form-group">
                        <label for="Tipo">Tipo de salario</label>
                        <select  class="form-control" id="tipo" name="Tipo" type="text" required="true">
                            <option >Salario integral</option>
                            <option>servicios</option>
                            <option>salario</option>

                        </select>
                    </div>
                </div>


                <br>

                <input type="submit" value="Guardar"  class="btn btn-primary"/> 
                <input type="reset" id="limpiar" value="limpiar" class="btn btn-primary" />
            </form>  
            <iframe name="pp" style="position:absolute; top:-1500px;"></iframe><!--para guardar los datos y quedar en la misma pagina-->
        </div>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
