<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <title>AMTProjectOne</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- Font Awesome -->
  <link rel="stylesheet" href="plugins/fontawesome-free/css/all.min.css" />
  <!-- Ionicons -->
  <link
          rel="stylesheet"
          href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css"
  />
  <!-- Tempusdominus Bbootstrap 4 -->
  <link
          rel="stylesheet"
          href="plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css"
  />
  <!-- iCheck -->
  <link
          rel="stylesheet"
          href="plugins/icheck-bootstrap/icheck-bootstrap.min.css"
  />
  <!-- JQVMap -->
  <link rel="stylesheet" href="plugins/jqvmap/jqvmap.min.css" />
  <!-- Theme style -->
  <link rel="stylesheet" href="dist/css/adminlte.min.css" />
  <!-- overlayScrollbars -->
  <link
          rel="stylesheet"
          href="plugins/overlayScrollbars/css/OverlayScrollbars.min.css"
  />
  <!-- Daterange picker -->
  <link rel="stylesheet" href="plugins/daterangepicker/daterangepicker.css" />
  <!-- summernote -->
  <link rel="stylesheet" href="plugins/summernote/summernote-bs4.css" />
  <!-- Google Font: Source Sans Pro -->
  <link
          href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
          rel="stylesheet"
  />
</head>
<body class="hold-transition sidebar-mini layout-fixed">
<div class="wrapper">
  <!-- Navbar -->
  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#"
        ><i class="fas fa-bars"></i
        ></a>
      </li>
    </ul>
  </nav>
  <!-- /.navbar -->

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    <!-- Brand Logo -->
    <a class="brand-link">
      <img
              src="dist/img/AdminLTELogo.png"
              alt="AdminLTE Logo"
              class="brand-image img-circle elevation-3"
              style="opacity: .8"
      />
      <span style="color: #9e9e9e" class="brand-text font-weight-light">AMTProjectOne</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
      <!-- Sidebar user panel (optional) -->
      <div class="user-panel mt-3 pb-3 mb-3 d-flex">
        <div class="info">
          <a class="d-block"><%=request.getSession().getAttribute("fullName")%></a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul
                class="nav nav-pills nav-sidebar flex-column"
                data-widget="treeview"
                role="menu"
                data-accordion="false"
        >
          <!-- Add icons to the links using the .nav-icon class
           with font-awesome or any other icon font library -->
          <li class="nav-item">
            <a href="<%=request.getContextPath()%>/" class="nav-link ">
              <i class="nav-icon fas fa-film"></i>
              <p>
                Films
                <i class="right fas"></i>
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link active">
              <i class="nav-icon fas fa-user-astronaut"></i>
              <p>
                Personnages
                <i class="right fas"></i>
              </p>
            </a>
          </li>
          <li class="nav-item">
            <a href="actor" class="nav-link">
              <i class="nav-icon fas fa-user"></i>
              <p>
                Acteur
                <i class="right fas"></i>
              </p>
            </a>
          </li>

        </ul>
      </nav>
      <div class="user-panel mt-3 pb-3 mb-3 d-flex" style="position:absolute;bottom:0;">
        <div class="info">
          <a href="logout" class="d-block">Se déconnecter</a>
        </div>
      </div>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <button onclick="location.href = '<%=request.getContextPath()%>/createCharacter';" type="submit" class="btn btn-secondary m-auto">Ajouter un personnage</button>
            <button onclick="location.href = '<%=request.getContextPath()%>/deleteCharacter';" type="submit" class="btn btn-secondary m-auto">Supprimer un personnage</button>
            <button onclick="location.href = '<%=request.getContextPath()%>/updateCharacter';" type="submit" class="btn btn-secondary m-auto">Modifier un personnage</button>
            <h1 class="mt-3">Liste des personnages</h1>
          </div>
          <div class="col-sm-6">
            <ol class="breadcrumb float-sm-right">
              <li class="breadcrumb-item"><a href="#">Accueil</a></li>
              <li class="breadcrumb-item active">Liste des personnages</li>
            </ol>
          </div>
        </div>
      </div>
      <!-- /.container-fluid -->
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-12">
          <div class="card">
            <!-- /.card-header -->
            <div class="card-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                  <td>ID du personnage</td>
                  <td>Nom du personnage</td>
                  <td>Nom du film</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${characters}" var="character">
                  <tr>
                    <td>${character.idChar}</td>
                    <td>${character.charName}</td>
                    <td>${character.movie.title}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <!-- /.container-fluid -->
    </section>
  </div>
  <!-- /.content -->
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <strong>Guillaume Vetter et Jael Dubey </strong>
    <div class="float-right d-none d-sm-inline-block">
      <b>Version</b> 1.0.0
    </div>
  </footer>

  <!-- Control Sidebar -->
  <aside class="control-sidebar control-sidebar-dark">
    <!-- Control sidebar content goes here -->
  </aside>
  <!-- /.control-sidebar -->
</div>
<!-- ./wrapper -->

<!-- jQuery -->
<script src="plugins/jquery/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="plugins/jquery-ui/jquery-ui.min.js"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge("uibutton", $.ui.button);
</script>
<!-- Bootstrap 4 -->
<script src="plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- ChartJS -->
<script src="plugins/chart.js/Chart.min.js"></script>
<!-- Sparkline -->
<script src="plugins/sparklines/sparkline.js"></script>
<!-- JQVMap -->
<script src="plugins/jqvmap/jquery.vmap.min.js"></script>
<script src="plugins/jqvmap/maps/jquery.vmap.usa.js"></script>
<!-- jQuery Knob Chart -->
<script src="plugins/jquery-knob/jquery.knob.min.js"></script>
<!-- daterangepicker -->
<script src="plugins/moment/moment.min.js"></script>
<script src="plugins/daterangepicker/daterangepicker.js"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js"></script>
<!-- Summernote -->
<script src="plugins/summernote/summernote-bs4.min.js"></script>
<!-- overlayScrollbars -->
<script src="plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
<!-- AdminLTE App -->
<script src="dist/js/adminlte.js"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="dist/js/pages/dashboard.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="dist/js/demo.js"></script>
<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
<!-- page script -->
<script>
    $(function () {
        $("#example1").DataTable();
        $('#example2').DataTable({
            processing: true,
            serverSide: true,
            ajax: {
            url: '<%=request.getContextPath() + "/characters"%>',
                type: 'POST'
            },
            columns: [{
                data: "idChar",
                mData: "idChar"
            }, {
                data: "charName",
                mData: "charName"
            }, {
                data: "movie.title",
                mData: "movie.title"
            }],
            "paging": true,
            "lengthChange": false,
            "searching": false,
            "ordering": true,
            "info": true,
            "autoWidth": false,
        });
    });
</script>
</body>
</html>
