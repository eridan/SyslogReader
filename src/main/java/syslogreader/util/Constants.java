package syslogreader.util;

import java.io.File;


public class Constants {
    
//    public static final String FS = System.getProperty("file.separator");
    public static final String FS = File.separator;
    public static final String CONFIG_FILE = ("/config/filter.config");
    
    
    public String txtfile = "";
    public String tableStatFileCntx = "";
    
    public static String utilFileCntx = 
            "var pattern = \"(\\\\w+\\\\.[a-z]{2,4})$\";\n" +
"\n" +
"function getDomain(domainStr) {\n" +
"	var regex = new RegExp(pattern,\"mg\");\n" +
"	var resultArray = new Array();\n" +
"	resultArray = regex.exec(domainStr);\n" +
"	\n" +
"	if(resultArray && resultArray.length>0) {\n" +
"		return resultArray[0];\n" +
"	} else {\n" +
"		console.error(\"No domain found for \"+domainStr);\n" +
"		return null;\n" +
"	}\n" +
"}\n" +
"\n" +
"function buildHashMapByIP(logArray) {\n" +
"	var hashmap = {};\n" +
"	if(logArray.length>0) {\n" +
"		var ipLogsArr = new Array();\n" +
"		ipLogsArr.push(logArray[0]);\n" +
"		hashmap[logArray[0].localIP] = ipLogsArr;\n" +
"	\n" +
"		for(i=1; i<logArray.length; i++) {\n" +
"			//console.log(logArray[i].toSource());\n" +
"			if(logArray[i].localIP in hashmap) {\n" +
"				hashmap[logArray[i].localIP].push(logArray[i]);\n" +
"			} else {\n" +
"				var ipLogsArr = new Array();\n" +
"				ipLogsArr.push(logArray[i]);\n" +
"				hashmap[logArray[i].localIP] = ipLogsArr;\n" +
"			}\n" +
"		}\n" +
"		//outputHashmap(hashmap);\n" +
"		return hashmap;\n" +
"	} else {\n" +
"		console.error(\"Error! util.js / buildHashmapByIp returns null!\");\n" +
"		return null;\n" +
"	}\n" +
"}\n" +
"\n" +
"function get_random_color() {\n" +
"	var colors = ['#428bca', '#5cb85c', '#5bc0de', '#f0ad4e', '#d9534f'];\n" +
"	var randNo = Math.floor((Math.random()*5));;\n" +
"	console.debug('randNo = '+randNo);\n" +
"    //return color;\n" +
"    return colors[randNo];\n" +
"}\n" +
"\n" +
"function buildBrowsingHistoryPerIP(hashmap) {\n" +
"	//$('#graphDiv').empty();\n" +
"	\n" +
"	var tabsDiv = $('<ul></ul>').attr('id','myTab');\n" +
"	tabsDiv.attr('class','nav nav-tabs');\n" +
"	var tabsCont = $('<div></div>').attr('id','myTabContent');\n" +
"	tabsCont.attr('class','tab-content');\n" +
"\n" +
"	for(var ip in hashmap) {\n" +
"		console.log('IP: '+ip);\n" +
"		tabsDiv.append(buildNewTabTitle(ip));\n" +
"		tabsCont.append(fillTabWithContents(ip, hashmap[ip]));\n" +
"		//console.log(hashmap[ip]);\n" +
"	}\n" +
"	\n" +
"	bindActionsToTheGraph();\n" +
"	$(\"#graphDiv\").append(tabsDiv);\n" +
"	$(\"#graphDiv\").append(tabsCont);\n" +
"}\n" +
"\n" +
"function buildNewTabTitle(ip) {\n" +
"	// Drawing Tabs for each IP address\n" +
"	var tabDiv = $('<li></li>');\n" +
"	var tabLink = $('<a></a>');\n" +
"	tabLink.attr('href','#'+ip.replace(/\\./g, '_' ));\n" +
"	tabLink.attr('data-toggle','tab');\n" +
"	//tabDiv.css(\"background-color\",get_random_color());\n" +
"	tabLink.append(ip);\n" +
"	tabDiv.append(tabLink);\n" +
"	return tabDiv;\n" +
"}\n" +
"\n" +
"function fillTabWithContents(ip, data) {\n" +
"	// Filling tabs with content\n" +
"	var h4Title = $('<H4></H4>').attr('class','graphTitle');\n" +
"	h4Title.append('Browsing history for IP <span>'+ip+'</span> <small class=\"hover\"></small>');\n" +
"	\n" +
"	var ipData = buildBrowsHistPerIPData(data);\n" +
"	var pieGraphDiv = drawGraphPerIP(ip,ipData);\n" +
"	var tabsContDiv = $('<div></div>').attr('id', ip.replace(/\\./g, '_' ));\n" +
"	tabsContDiv.attr('class','tab-pane fade');\n" +
"	tabsContDiv.append(h4Title);\n" +
"	tabsContDiv.append(pieGraphDiv);\n" +
"	return tabsContDiv;\n" +
"}\n" +
"\n" +
"function bindActionsToTheGraph() {\n" +
"	$(\"#graphDiv\").bind(\"plothover\", function(event, pos, obj) {\n" +
"		if (!obj) {\n" +
"			$(\".hover\").empty();\n" +
"			return;\n" +
"		}\n" +
"		var percent = parseFloat(obj.series.percent).toFixed(2);\n" +
"		$(\".hover\").css({'color':obj.series.color});\n" +
"		$(\".hover\").text(obj.series.label + \" (\" + percent + \"%)\");\n" +
"	});\n" +
"	\n" +
"	\n" +
"	$(\"#graphDiv\").bind(\"plotclick\", function(event, pos, obj) {\n" +
"		if (!obj) {\n" +
"			return;\n" +
"		}\n" +
"		percent = parseFloat(obj.series.percent).toFixed(2);\n" +
"		alert(\"\"  + obj.series.label + \": \" + percent + \"%\");\n" +
"	});\n" +
"}\n" +
"\n" +
"function drawGraphPerIP(ip, ipData) {\n" +
"	var pieGraphDiv = $('<div></div>').attr('id',ip.replace(/\\./g, '_' )+'_graph');\n" +
"\n" +
"	var data = [];\n" +
"	for (var key in ipData) {\n" +
"		data.push( {\n" +
"			label: 'Domain name: [' + key + ']',\n" +
"			data: ipData[key]\n" +
"		});\n" +
"	}\n" +
"	\n" +
"	var pieChartDiv = $('<div></div>').attr('class','graph');\n" +
"	pieChartDiv.css('width','440px');\n" +
"	pieChartDiv.css('height','240px');\n" +
"	\n" +
"	$(\"#graphDiv\").append(pieChartDiv);\n" +
"	\n" +
"	$.plot(pieChartDiv, data, {\n" +
"    series: {\n" +
"        pie: {\n" +
"            show: true\n" +
"        }\n" +
"    },\n" +
"    grid: {\n" +
"        hoverable: true,\n" +
"        clickable: true\n" +
"    }\n" +
"	});\n" +
"	\n" +
"	pieGraphDiv.append(pieChartDiv);\n" +
"	return pieGraphDiv;\n" +
"}\n" +
"\n" +
"function updateGraphOnlyForIP(ip, rawData) {\n" +
"	 // var domEl = document.getElementById(ip+'_div');\n" +
"	 var ipStr = ip.replace(/\\./g, '_' );\n" +
"	 //alert(\"ip [\"+ipStr+\"]\");\n" +
"	//var domEl = $('#myTabContent').find(ipStr);\n" +
"	var domEl = document.getElementById(ipStr+'_graph');\n" +
"	\n" +
"	document.write(domEl.html());\n" +
"	// var domEl = $('#myTabContent');\n" +
"	\n" +
"\n" +
"	//var arr = $('div > span').parent();\n" +
"\n" +
"\n" +
"	//alert(domEl);\n" +
"	console.debug( domEl.get() );\n" +
"	domEl.show();\n" +
"	domEl.remove();\n" +
"}\n" +
"\n" +
"function buildBrowsHistPerIPData(browsHistArray) {\n" +
"	console.log('Browsing History Array: '+browsHistArray.toSource());\n" +
"	browsHistMap = {};\n" +
"	\n" +
"	var domArray = new Array();\n" +
"	for(i=0; i<browsHistArray.length; i++) {\n" +
"		//console.log('Domain ['+browsHistArray[i].destDomain+'] found');\n" +
"		var domainName = getDomain(browsHistArray[i].destDomain);\n" +
"		if(domainName != null) {\n" +
"			if(domainName in browsHistMap) {\n" +
"				browsHistMap[domainName] = browsHistMap[domainName] + 1;\n" +
"			} else {\n" +
"				browsHistMap[domainName] = 1;\n" +
"			}\n" +
"		}\n" +
"	}\n" +
"	\n" +
"	return browsHistMap;\n" +
"	console.log(browsHistMap.toSource());\n" +
"}\n" +
"\n" +
"\n" +
"function outputHashmap(hashmap) {\n" +
"	for(var key in hashmap) {\n" +
"		console.log(key);\n" +
"		console.log(hashmap[key]);\n" +
"	}\n" +
"}";
    
    public static String htmlFileCntx =
            "<!DOCTYPE html>\n" +
"<html>\n" +
"<head>\n" +
"<!--  -->\n" +
"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
"\n" +
"<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>\n" +
"<script src=\"http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js\"></script>\n" +
"<script src=\"http://www.flotcharts.org/flot/jquery.flot.js\"></script>\n" +
"<script src=\"http://www.flotcharts.org/flot/jquery.flot.pie.js\"></script>\n" +
"<link rel=\"stylesheet\" href=\"http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css\"></link>\n" +
"<link rel=\"stylesheet\" href=\"http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables_themeroller.css\"></link>\n" +
"\n" +
"\n" +
" <!-- Le javascript\n" +
"    ================================================== -->\n" +
"    <!-- Placed at the end of the document so the pages load faster -->\n" +
"	\n" +
"	<!-- Latest compiled and minified CSS -->\n" +
"	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css\">\n" +
"\n" +
"	<!-- Optional theme -->\n" +
"	<link rel=\"stylesheet\" href=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap-theme.min.css\">\n" +
"\n" +
"	<!-- Latest compiled and minified JavaScript -->\n" +
"	<script src=\"http://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js\"></script>\n" +
"	\n" +
"<script src=\"tableStat.js\"></script>\n" +
"<script src=\"util.js\"></script>\n" +
"\n" +
"<title>Syslog Statistics</title>\n" +
"<style>\n" +
"	body {\n" +
"		padding:30px;\n" +
"	}\n" +
"</style>\n" +
"\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"	<h1 class = \"masterhead\" >Browsing History read from the Syslog <small>file <span id=fileName></span></small></h1>\n" +
"	<br />\n" +
"	<button id=\"refreshButton\" type=\"button\" class=\"btn btn-primary\" data-toggle=\"tooltip\" data-placement=\"auto\" title=\"Pulling data from the file\">Refresh Data from the file</button>\n" +
"	<br />\n" +
"		\n" +
"	<div class=\"panel panel-default\">\n" +
"	  <div class=\"panel-heading\"><h4 id=\"graphPanelTitle\">Graph View <small>as at <span class=curDateTime></span></small></h4></div>\n" +
"		<div class=\"panel-body\">\n" +
"			<div id=graphDiv></div>\n" +
"		</div>\n" +
"	</div>\n" +
"	\n" +
"<br />\n" +
"\n" +
"	<div class=\"panel panel-default\">\n" +
"	  <div class=\"panel-heading\"><h4 id=\"tablePanelTitle\" >Data Table View <small>as at <span class=curDateTime></span></small></h4></div>\n" +
"	  <div class=\"panel-body\">\n" +
"		<div id=tableDiv></div>\n" +
"	  </div>\n" +
"	</div>\n" +
"<br />\n" +
"\n" +
"\n" +
"<footer>\n" +
"	<p>File created: <span id=curDateTime></span><p>\n" +
"	<p> File name: <span id=fileName></span></p>\n" +
"</footer>\n" +
"\n" +
"</body>\n" +
"</html>";

    public String getTxtfile() {
        return txtfile;
    }

    public void setTxtfile(String txtfile) {
        this.txtfile = txtfile;
    }

    public String getTableStatFileCntx() {
        this.tableStatFileCntx =
            "var dateStr = new Date().toString();\n" +
"var url = '"+this.getTxtfile()+"';\n" +
"var browsHistHashMap = {};\n" +
"\n" +
"function LogData(date, time, localIP, destDomain) {\n" +
"	this.date=date;\n" +
"	this.time=time;\n" +
"	this.localIP=localIP;\n" +
"	this.destDomain=destDomain;		\n" +
"}\n" +
"\n" +
"$(document).ready(function() {\n" +
"	$(\".curDateTime\").append(dateStr.slice(3,24));\n" +
"	$(\"#fileName\").append(url);\n" +
"\n" +
"	// Initialize tooltip\n" +
"	$(function () {\n" +
"        $(\"[data-toggle='tooltip']\").tooltip();\n" +
"    });\n" +
"	\n" +
"	// Initialize tabs\n" +
"	$('#myTab a').click(function (e) {\n" +
"		e.preventDefault()\n" +
"		$(this).tab('show')\n" +
"	});\n" +
"	\n" +
"	// Initialize popover\n" +
"	$(\"#graphPanelTitle\").popover({ title: 'Graph View', content: \"Click on the IP address to see its graph\", placement: 'top', trigger: 'hover' });\n" +
"	$(\"#tablePanelTitle\").popover({ title: 'Table View', content: \"Here you can find all the _UNSORTED_ data\", placement: 'top', trigger: 'hover' });\n" +
"	$(\"#graphPanelTitle\").css('display','inline-block');\n" +
"	$(\"#tablePanelTitle\").css('display','inline-block');\n" +
"	\n" +
"	readTheDataFile(url);\n" +
"	\n" +
"	$(\"#refreshButton\").css('margin','10px');\n" +
"	$(\"#refreshButton\").css('text-align','right');\n" +
"	\n" +
"	$(\".masterhead\").css('text-align','center');\n" +
"	//$(\".panel-body\").css('background-color','#eee');\n" +
"	\n" +
"	$(\"#refreshButton\").click(function(){\n" +
"		//readTheDataFile(url);\n" +
"		updateData();\n" +
"	});\n" +
"	\n" +
"	\n" +
"	\n" +
"	// Add the Flot version string to the footer\n" +
"	var flotVerP = $('<p></p>').attr('id','flot_ver');\n" +
"	flotVerP.prepend(\"Flot \" + $.plot.version + \" &ndash; \");\n" +
"	$(\"footer\").append(flotVerP);\n" +
"});\n" +
"\n" +
"function updateData() {\n" +
"	var logArray = new Array();\n" +
"	\n" +
"	$.ajax(url, {\n" +
"		dataType: 'text',\n" +
"		success: function (data) {\n" +
"			var logStr = data.slice(0,data.length-3); 	// deleting the last ','\n" +
"			logStr = '['+logStr+']';					// putting it within brackets\n" +
"			\n" +
"			$.each($.parseJSON(logStr), function(idx, obj) {\n" +
"				var log = new LogData(obj.date, obj.time, obj.localIP, obj.destDomain);\n" +
"				logArray.push(log);\n" +
"			});\n" +
"			\n" +
"			// Update Table\n" +
"			\n" +
"			createTable(logArray);\n" +
"			\n" +
"			// Update Graph\n" +
"			\n" +
"			var hashmap = {};\n" +
"			hashmap = buildHashMapByIP(logArray);\n" +
"			\n" +
"			for(var ip in hashmap) {\n" +
"				if(browsHistHashMap[ip]) {\n" +
"					console.debug('IP already exists, updating the Graph');\n" +
"					updateGraphOnlyForIP(ip,hashmap[ip]);\n" +
"				} else {\n" +
"					console.debug('IP not found, adding new tab');\n" +
"					updateTabsAndGraph(ip,hashmap[ip]);\n" +
"				}\n" +
"			}\n" +
"		}\n" +
"	});\n" +
"}\n" +
"\n" +
"\n" +
"function readTheDataFile(url) {\n" +
"	var logArray = new Array();\n" +
"	\n" +
"	$.ajax(url, {\n" +
"		dataType: 'text',\n" +
"		success: function (data) {\n" +
"			var logStr = data.slice(0,data.length-3); 	// deleting the last ','\n" +
"			logStr = '['+logStr+']';					// putting it within brackets\n" +
"			\n" +
"			$.each($.parseJSON(logStr), function(idx, obj) {\n" +
"				var log = new LogData(obj.date, obj.time, obj.localIP, obj.destDomain);\n" +
"				logArray.push(log);\n" +
"			});\n" +
"			\n" +
"			// Draw Table\n" +
"			\n" +
"			createTable(logArray);\n" +
"			\n" +
"			// Draw Graph\n" +
"			\n" +
"			browsHistHashMap = buildHashMapByIP(logArray);\n" +
"	\n" +
"			if(browsHistHashMap) {\n" +
"				buildBrowsingHistoryPerIP(browsHistHashMap);\n" +
"			} else {\n" +
"				alert(\"Sorry, something went seriously wrong!\");\n" +
"			}\n" +
"		}\n" +
"	});\n" +
"}\n" +
"\n" +
"function displayArrayLength(logArray) {\n" +
"	console.log('After Ajax Array: '+logArray.length);\n" +
"}\n" +
"\n" +
"function createTable(logArray) {\n" +
"	var tableDiv = $(\"#tableDiv\");\n" +
"	tableDiv.empty();\n" +
"	\n" +
"	var table = $('<table></table>').attr('id','logsTable');\n" +
"	var thead = $('<thead></thead>');\n" +
"	var row = $('<tr></tr>');\n" +
"	var th1 = $('<th></th>').text('Date');\n" +
"	var th2 = $('<th></th>').text('Time');\n" +
"	var th3 = $('<th></th>').text('LocalIP');\n" +
"	var th4 = $('<th></th>').text('Dest. Domain');\n" +
"	row.append(th1);\n" +
"	row.append(th2);\n" +
"	row.append(th3);\n" +
"	row.append(th4);\n" +
"	thead.append(row);\n" +
"	\n" +
"	table.append(thead);\n" +
"	\n" +
"	$.each(logArray, function(idx, log) {\n" +
"		var row = $('<tr></tr>');\n" +
"		var col1 = $('<td></td>').text(log.date);\n" +
"		var col2 = $('<td></td>').text(log.time);\n" +
"		var col3 = $('<td></td>').text(log.localIP);\n" +
"		var col4 = $('<td></td>').text(log.destDomain);\n" +
"		row.append(col1);\n" +
"		row.append(col2);\n" +
"		row.append(col3);\n" +
"		row.append(col4);\n" +
"		table.append(row);\n" +
"	});\n" +
"	\n" +
"	tableDiv.append(table);\n" +
"	$('#logsTable').dataTable( {\n" +
"        \"aaSorting\": [[ 1, \"desc\" ]]\n" +
"    } );\n" +
"}";
        return this.tableStatFileCntx;
    }
    
}
