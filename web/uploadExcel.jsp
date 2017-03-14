
<%@page import="entidades.CargarFechas"%>
<%@page import="entidades.GenerarFechas"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content ="IE=edge">
        <meta name="viewport" content="width=device-width, initial-Scale=1">
        <link rel="stylesheet" href="css/estilos.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <!-- esta etiqueta es para detectar el tamaño de la pantalla del usuario y para adactar el tamaño de la pantalla a los dispositivos--> 
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, minium-scale=1.0">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
        <!--<script type="text/javascript">
            $(document).ready(function () {
                $(".campofecha").calendarioDW();
            });
        </script>-->       

        <title>JSP Page</title>
    </head>
    <body >
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
                        <li><a href="RegistroM.jsp">Registro empleado</a></li>
                        <li><a href="Administracion.jsp">Administracion</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <br>
        <br>
        <br>
        <br>
        <br>
        <section class="main">
            <div class = "container">
                <form class = "container" method="post"  action="GenerarFechasArchivos.do">



                    <h1 class="title">Generar Meses</h1>
                    <div class="form-group"> <!--esta clase sirve para dar un espacion entre label-->
                        <label for="Starting_year">Año Inicio</label>
                        <input class="form-control"name="Starting_year" id="Starting_year" type="number" placeholder="aaaa"required="true"/>
                    </div>
                    <div class="form-group">
                        <label for="Mes_Inicio">Mes Inicio</label>
                        <input class="form-control"id="Mes_Inicio" name="Mes_Inicio" type="number" placeholder="mm" required="true"/>
                    </div>
                    <div class="form-group">
                        <lable for ="Final_year">Año Fin</lable>
                        <input class="form-control" id ="Final_year" name="Final_year" placeholder="aaaa" type ="number"/>

                    </div>
                    <div class="form-group">
                        <label for="Mes_Fin">Mes Fin</label>
                        <input class="form-control" id ="Mes_Fin"  name="Mes_Fin" type="number" placeholder="mm"/>
                    </div>

                    <input type="submit" value="Generar"  class="btn btn-primary"/>



                </form>
                <form method="post" action="SeleccionarFechas.do">
                    <input type="submit" value="Cargar Fechas"  class="btn btn-primary"/>
                </form>

            </div>
        </section>
        <section class="main">
            <h1 class="title">Archivos</h1>
            <article>
                <p>
                    <br>
                    <br>


                <div class="container">
                    <form action="UploadfileHorasExtras.do" method="post" enctype="multipart/form-data">
                        <table border="2">
                            <tr>
                                <td align="center">Archivo Horas Extras</td>

                            </tr>
                            <tr>
                                <td>
                                    <input id="file3" type="file" name="file" required="true">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" name="submit" value="subir" onclick="return confirm('estas seguro de cargar este archivo')"/>
                                </td>
                            </tr>
                        </table>

                    </form>
                    <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>
                    <br>
                    <br>
                    <br>
                </div>
                <div class="container">
                    <form  action ="uploadExcel"  method="post"  enctype="multipart/form-data"  >

                        <table border="2">
                            <tr>
                                <td align="center">Seleccionar archivo de costos</td>
                            </tr>
                            <tr>
                                <td>
                                    <input id="file2" type="file" name="file" id="file" required="true"/>
                                </td>
                            </tr>

                            <tr>
                                <td align = "center">
                                    <input type="submit" name="submit"value="subir" onclick=" return confirm('Estas seguro de cargar este archivo')" />

                                </td>
                            </tr>
                        </table>

                    </form>
                    <!--  <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>-->

                </div>
            </article>
        </section>

        <%
            ArrayList<CargarFechas> fechas = (ArrayList<CargarFechas>) request.getAttribute("fecha");

        %>
        <section class="main">
            <article>
                <p>
                <div class="container">
                    <form name="form" action ="uplaodExcel2"  method="post"  enctype="multipart/form-data">
                        <table border="2">


                            <!--    <h1 class="title"></h1>-->
                            <!-- <div class="form-group">-->
                            <!-- <label for="Fecha"> Por favor ingresa la fecha del archivo a subir</label>-->
                            <!--  <h1 class="title">Registro empleados</h1> -->
                            <!--    <div class="form-group"> <!--esta clase sirve para dar un espacion entre label-->
                            <!--   <h4>Por favor ingrese la fecha del archivo a subir</h4> -->
                            <!--<label for="Fecha">Fecha</label>-->
                            <!--<input type="text" name="Fecha" class="fecha" size="9" placeholder="Mes.Año" required="true"/>-->
                            <!--</div> -->
                            <!--</div>-->

                            <h1 class="title">Archivo Jira</h1>
                            <div class="form-group">

                                <label for="Fecha">Fecha</label>
                                <select  class="form-control"  name="Fecha" type="text" required="true">

                                    <%for (CargarFechas fecha : fechas) {%>
                                    <option value="<%=fecha.getYear_Meses()%>"><%= fecha.getYear_Meses()%></option>

                                    <%}%>                    
                                </select>

                            </div>



                            <br>
                            <br>

                            <tr>
                                <td align="center">Seleccionar archivo de Jira</td>
                            </tr>
                            <tr>
                                <td>
                                    <input id="file2" type="file" name="file" id="file" required="true"/>
                                </td>
                            </tr>

                            <tr>
                                <td align = "center">
                                    <input type="submit" name="submit" value="subir"  onclick= " return confirm('Estar seguro de subir este archivo')"/>

                                </td>
                            </tr>
                        </table>

                    </form>
                </div>


                <!--   <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>-->



                <iframe name="pp" style="position:absolute; top:-1500px;"></iframe>
            </article>
        </section>
        <div align="center">
            <form id="downloadBanned" action="generariforme.do" method="post">`
                <input type="submit" value="Generar" class = "btn btn-primary"><br>            `
            </form>

    </body>
</html>
