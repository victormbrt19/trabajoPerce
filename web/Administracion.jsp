<%-- 
    Document   : Administracion
    Created on : 16/11/2016, 03:01:15 PM
    Author     : pcc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content ="IE=edge">
        <meta name="viewport" content="width=device-width, initial-Scale=1">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minium-scale=1.0">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="calendario_dw/calendario_dw-estilos.css" type="text/css" rel="stylesheet">
        <script type="text/javascript" src="calendario_dw/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="calendario_dw/calendario_dw.js"></script>
        <link href="calendario_dw/calendario_dw-estilos.css" type="text/css" rel="STYLESHEET">
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <style type="text/css">

        </style>
        <script type="text/javascript" src="calendario_dw/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="calendario_dw/calendario_dw.js"></script>

        <script type="text/javascript">
            $(document).ready(function () {
                $(".campofecha").calendarioDW();
            });
        </script>        

        <title>JSP Page</title>
    </head>
</head>
<body>

    <header>
        <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Menu</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="index.jsp" class="navbar-brand" >CostosPerceptio</a>
                </div>


                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Inicio</a></li> 
                        <li class="active"><a href="RegistroM.jsp">Registro<span class="sr-only"></span></a></li>
                        <li><a href="uploadExcel.jsp">Subir Archivo</a></li>

                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav> 

    </header>
</head>

<br>
<br>
<br>
<section class="main">
    <article>
        <p>
        <p>
        <form class = "container" action="uploadExcelNovedad.do" method="post" enctype="multipart/form-data">
            <table border="2">
                <h1 class="title">administracion</h1>
                <div class="form-group">
                    <label for="Fecha"> Fecha </label>
                    <input type="text" name="Fecha" class="campofecha" size="12">
                </div>
                <br>
                <tr>
                    <td align="center"> Seleccionar archivo de novedades</td>
                </tr>
                <tr>
                    <td>
                        <input id="file2" type="file" name="file" id="file" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align = "center">
                        <input type="submit" name="submit"value="subir" onclick=" return confirm('Estas seguro de cargar este archivo')"/>
                    </td>
                </tr>
            </table>
        </form>


        <br>
        <br>
        <br>
        <form class = "container" action="uploadExcelNombres.do" method="post" enctype="multipart/form-data">
            <table border="2">
                <tr>
                    <td align="center"> Seleccionar archivo de nombres</td>
                </tr>
                <tr>
                    <td>
                        <input id="file2" type="file" name="file" id="file" required="true"/>
                    </td>
                </tr>
                <tr>

                <tr>
                    <td align = "center">
                        <input type="submit" name="submit"value="subir" onclick=" return confirm('Estas seguro de cargar este archivo')"/>
                    </td>
                </tr>
            </table>
        </form>

        <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>

        <form class = "container" action="UploadEspecialidad.do" method="post" enctype="multipart/form-data">
            <table border="2">
                <tr>
                    <td align="center"> Archivo de Especialidad</td>
                </tr>
                <tr>
                    <td>
                        <input id="file2" type="file" name="file" id="file" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align = "center">
                        <input type="submit" name="submit"value="subir" onclick=" return confirm('Estas seguro de cargar este archivo')"/>
                    </td>
                </tr>
            </table>
        </form>

        <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>
        <br>
        <br>
        <form class = "container" action="ListaDeProyectos.do" method="post" enctype="multipart/form-data">
            <table border="2">
                <tr>
                    <td align="center"> Archivo Lista de proyectos</td>
                </tr>
                <tr>
                    <td>
                        <input id="file2" type="file" name="file" id="file" required="true"/>
                    </td>
                </tr>
                <tr>
                    <td align = "center">
                        <input type="submit" name="submit"value="subir" onclick=" return confirm('Estas seguro de cargar este archivo')"/>
                    </td>
                </tr>
            </table>
        </form>

        <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>

        </body>
        </html>