/*!
    * Start Bootstrap - Freelancer v6.0.0 (https://startbootstrap.com/themes/freelancer)
    * Copyright 2013-2020 Start Bootstrap
    * Licensed under MIT (https://github.com/BlackrockDigital/startbootstrap-freelancer/blob/master/LICENSE)
    */
    (function($) {
    "use strict"; // Start of use strict
  
    // Smooth scrolling using jQuery easing
    $('a.js-scroll-trigger[href*="#"]:not([href="#"])').click(function() {
      if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
        var target = $(this.hash);
        target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
        if (target.length) {
          $('html, body').animate({
            scrollTop: (target.offset().top - 71)
          }, 1000, "easeInOutExpo");
          return false;
        }
      }
    });

  
    // Scroll to top button appear
    $(document).scroll(function() {
      var scrollDistance = $(this).scrollTop();
      if (scrollDistance > 100) {
        $('.scroll-to-top').fadeIn();
      } else {
        $('.scroll-to-top').fadeOut();
      }
    });
  
    // Closes responsive menu when a scroll trigger link is clicked
    $('.js-scroll-trigger').click(function() {
      $('.navbar-collapse').collapse('hide');
    });
  
    // Activate scrollspy to add active class to navbar items on scroll
    $('body').scrollspy({
      target: '#mainNav',
      offset: 80
    });

  
    // Collapse Navbar
    var navbarCollapse = function() {
      if ($("#mainNav").offset().top > 100) {
        $("#mainNav").addClass("navbar-shrink");
      } else {
        $("#mainNav").removeClass("navbar-shrink");
      }
    };
    // Collapse now if page is not at top
    navbarCollapse();
    // Collapse the navbar when page is scrolled
    $(window).scroll(navbarCollapse);
  
    // Floating label headings for the contact form
    $(function() {
      $("body").on("input propertychange", ".floating-label-form-group", function(e) {
        $(this).toggleClass("floating-label-form-group-with-value", !!$(e.target).val());
      }).on("focus", ".floating-label-form-group", function() {
        $(this).addClass("floating-label-form-group-with-focus");
      }).on("blur", ".floating-label-form-group", function() {
        $(this).removeClass("floating-label-form-group-with-focus");
      });
    });
  
  })(jQuery); // End of use strict


  
  function mensajeConfirmacionSalida() {

    swal({
      title: 'Mensaje',
      text: 'Datos almacenados satisfactoriamente',
      html: '<p>Mensaje de texto con <strong>formato</strong>.</p>',
      type: 'success',
    });
  }

  function mensajeConfirmacion()
  {
    $('.entradaBtn').on('click', function(event){
      event.preventDefault();

      $('.myForm #fecha').val('');
      $('.myForm #codigo').val('');
      $('.myForm #detalles').val('');
      $('.myForm #cantidad').val('');
      $('.myForm #costoUnitario').val('');
      $('.myForm #costoTotal').val('');
      $(',myForm #exampleModal').modal();

      swal({
        title: 'Mensaje',
        text: 'Datos almacenados satisfactoriamente',
        html: '<p>Mensaje de texto con <strong>formato</strong>.</p>',
        type: 'success',
      });
    });
  }

  function mensajeConfirmacionFacturaDeVenta() {

    swal({
      title: 'Mensaje',
      text: 'Datos almacenados satisfactoriamente',
      html: '<p><strong>Datos almacenados correctamente</strong>.</p>',
      type: 'success',
    });
  }

  $(document).ready(function() {
    $('.eBtn').on('click', function(event) {
          event.preventDefault();
          
          var href = $(this).attr('href');
          $.get(href, function(articulo, status){
              $('.entradaForm #nombre').val(articulo.name);
              $('.entradaForm #costoUnitario').val(articulo.unit_cost);
          });
          $('.entradaForm #entradaModal').modal();
    });
  });

  $(document).ready(function() {
    $('.sBtn').on('click', function(event) {
          event.preventDefault();
          
          var href = $(this).attr('href');
          $.get(href, function(articulo, status){
              $('.salidaForm #nombre').val(articulo.name);
              $('.salidaForm #costoUnitario').val(articulo.unit_cost);
          });
          $('.salidaForm #salidaModal').modal();
    });
  });
