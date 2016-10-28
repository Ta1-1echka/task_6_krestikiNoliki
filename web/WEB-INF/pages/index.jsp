<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            $('td').on('click', function (e) {
                $('#hidden_tr').val($(this).parent().attr('id'));
                $('#hidden_td').val($(this).attr('class'));
                $("form:first").trigger("submit");

            });


        });
    </script>
    <style>
        td {
            empty-cells: show;
            width: 80px;
            height: 80px;
        }

        table {
            border-collapse: collapse;
        }

        td:hover {
            background: red;
        }

        img {
            width: 80px;
            height: 80px;
        }

        .button {
            background-color: white;
            color: black;
            border: 2px solid #4CAF50; /* Green */
            size: 15px;

        }

    </style>
</head>
<body>
<form method="POST" action="/move" modelAttribute="player">
    <input type="hidden" id="hidden_tr" name="hidden_tr">
    <input type="hidden" id="hidden_td" name="hidden_td">
    <table align="center">
        <tr id="0">
            <td class="0"><img src="${src00}" class="img"/></td>
            <td class="1"><img src="${src01}"/></td>
            <td class="2"><img src="${src02}"/></td>
        </tr>
        <tr id="1">
            <td class="0"><img src="${src10}"/></td>
            <td class="1"><img src="${src11}"/></td>
            <td class="2"><img src="${src12}"/></td>
        </tr>
        <tr id="2">
            <td class="0"><img src="${src20}"/></td>
            <td class="1"><img src="${src21}"/></td>
            <td class="2"><img src="${src22}"/></td>
        </tr>
    </table>
    <p align="center">${message}</p>
</form>
<form method="GET" action="/" >
    <p align="center"><input type="submit" value="Начать заново" class="button"></p>
</form>
</body>
</html>