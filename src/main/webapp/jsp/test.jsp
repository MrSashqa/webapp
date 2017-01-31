<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <style>
        form#multiphase {
            border: #000 1px solid;
            padding: 24px;
            width: 350px;
        }

        form#multiphase > #phase2, #phase3, #show_all_data {
            display: none;
        }

    </style>


    <script>
        var fname, lname, gender, country;
        function _(x) {
            return document.getElementById(x);
        }

        function processPhase1() {
            fname = _("firstname").value;
            lname = _("lastname").value;
            if (fname.length > 2 && lname.length > 2) {
                _("phase1").style.display = "none";
                _("phase2").style.display = "block";
                _("progressBar").value = 33;
                _("status").innerHTML = "Phase 2 of 3";
            } else {
                alert("Please fill in the fields")
            }
        }

        function processPhase2() {
            gender = _("gender").value;
            if (gender.length > 0) {
                _("phase2").style.display = "none";
                _("phase3").style.display = "block";
                _("progressBar").value = 66;
                _("status").innerHTML = "Phase 3 of 3";

            } else {
                alert("Please choose your gender");
            }
        }


        function processPhase3() {
            country = _("country").value;
            if (country.length > 0) {
                _("phase3").style.display = "none";
                _("show_all_data").style.display = "block";
                _("display_fname").innerHTML = fname;
                _("display_lname").innerHTML = lname;
                _("display_gender").innerHTML = gender;
                _("display_country").innerHTML = country;
                _("progressBar").value = 100;
                _("status").innerHTML = "Data overview";

            } else {
                alert("Please choose your country");
            }

            function submitForm() {
                _("multiphase").method = "post";
                _("multiphase").submit();
            }
        }

        function _(x) {
            return document.getElementById(x);
        }
        function show(id) {
            var divelement = document.getElementById(id);
            if (divelement.style.display == 'none') {
                divelement.style.display = 'block';
            }

            else {
                divelement.style.display = 'none'
            }
        }
        i = 0;
        function increase() {
            i++;
            _('increase').innerHTML = i;
        }


    </script>
    <title>Title</title>
</head>
<body>
<input type="button" id="btn" value="increaseButton" onclick="increase()">
<div id="increase">1</div>


<progress id="progressBar" value="0" max="100" style="width: 250px;"></progress>
<h3 id="status">Phase 1 of 3</h3>
<form id="multiphase" onsubmit="return false">
    <div id="phase1">
        First Name: <input id="firstname" name="firstname"><br/>
        Last Name: <input id="lastname" name="lasttname"><br/>
        <button onclick="processPhase1()">continue</button>
    </div>
    <div id="phase2">
        Gender <select id="gender" name="gender">
        <option value=""></option>
        <option value="m">Male</option>
        <option value="f">Female</option>
    </select><br/>
        <button onclick="processPhase2()">continue</button>
    </div>
    <div id="phase3">
        Country:<select id="country" name="country">
        <option value="United States">United States</option>
        <option value="United Kingdom">United Kingdom</option>
        <option value="India">India</option>
    </select><br/>
        <button onclick="processPhase3()">continue</button>
    </div>
    <div id="show_all_data">
        First Name: <span id="display_fname"></span><br/>
        Last Name: <span id="display_lname"></span><br/>
        Gender: <span id="display_gender"></span><br/>
        Country:<span id="display_country"></span><br/>
        <button onclick="submitForm()">Submit Data</button>
    </div>

</form>

<button onclick="show('aaa')">AAA</button>
<div id="aaa">THis is my diw</div>


<button onclick="show('1')">AAA</button>
<div id="1">THis is my diw</div>

${pageContext.request.requestURI}

</body>
</html>
