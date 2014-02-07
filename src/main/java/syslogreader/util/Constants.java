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
"	//alert(\"Parameter: \"+domainStr);\n" +
"	var regex = new RegExp(pattern,\"mg\");\n" +
"	var resultArray = new Array();\n" +
"	resultArray = regex.exec(domainStr);\n" +
"	\n" +
"	if(resultArray && resultArray.length>0) {\n" +
"		//alert(\"util.js/getDomain says: \"+resultArray[0]);\n" +
"		return resultArray[0];\n" +
"	} else {\n" +
"		console.error(\"No domain found for \"+domainStr);\n" +
"		return null;\n" +
"	}\n" +
"}\n" +
"\n" +
"function buildHashMapByIP(logArray) {\n" +
"	var hashmap = {};\n" +
"	\n" +
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
"function buildBrowsingHistoryPerIP(hashmap) {\n" +
"	for(var key in hashmap) {\n" +
"		console.log('IP: '+key);\n" +
"		var ipData = buildBrowsHistPerIPData(hashmap[key]);\n" +
"		drawGraphPerIP(key,ipData);\n" +
"		\n" +
"		//console.log(hashmap[key]);\n" +
"	}\n" +
"}\n" +
"\n" +
"function drawGraphPerIP(ip, ipData) {\n" +
"	var data = [];\n" +
"	for (var key in ipData) {\n" +
"		data.push( {\n" +
"			label: 'Domain name: [' + key + ']',\n" +
"			data: ipData[key]\n" +
"		});\n" +
"	}\n" +
"	//console.log(data.toSource());\n" +
"	var ipDiv = $('<div></div>').attr('id',ip+'_div');\n" +
"	var h4Title = $('<H4></H4>').attr('class','graphTitle');\n" +
"	h4Title.append('Browsing history for IP <span>'+ip+'</span>');\n" +
"	ipDiv.append(h4Title);\n" +
"	var pieChartDiv = $('<div></div>').attr('class','graph');\n" +
"	pieChartDiv.css('width','440px');\n" +
"	pieChartDiv.css('height','240px');\n" +
"	$(\"#graphDiv\").append(pieChartDiv);\n" +
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
"	$(\"#graphDiv\").append(ipDiv);\n" +
"	$(\"#graphDiv\").append(pieChartDiv);\n" +
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
"<script src=\"tableStat.js\"></script>\n" +
"<script src=\"util.js\"></script>\n" +
"<title>Syslog Statistics</title>\n" +
"<style>\n" +
"	body {\n" +
"		margin: 30px; padding:30px;\n" +
"		text-align:center;\n" +
"	}\n" +
"</style>\n" +
"\n" +
"</head>\n" +
"<body>\n" +
"\n" +
"<h1>Browsing History read from the Syslog <small>Read from the file <span id=fileName></span></small></h1>\n" +
"<br />\n" +
"<div class=\"panel panel-default\">\n" +
"  <div class=\"panel-heading\"><h4>Graph View <small>as at <span id=curDateTime></span></small></h4></div>\n" +
"  <div class=\"panel-body\">\n" +
"    <div id=graphDiv></div>\n" +
"  </div>\n" +
"</div>\n" +
"<br />\n" +
"<div class=\"panel panel-default\">\n" +
"  <div class=\"panel-heading\"><h4>Data Table View <small>as at <span id=curDateTime></span></small></h4></div>\n" +
"  <div class=\"panel-body\">\n" +
"    <div id=tableDiv></div>\n" +
"  </div>\n" +
"</div>\n" +
"<br />\n" +
"\n" +
"\n" +
"<footer>\n" +
"	<p>File created: <span id=curDateTime></span><p>\n" +
"	<p> File name: <span id=fileName></span></p>\n" +
"</footer>\n" +
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
"\n" +
"function LogData(date, time, localIP, destDomain) {\n" +
"	this.date=date;\n" +
"	this.time=time;\n" +
"	this.localIP=localIP;\n" +
"	this.destDomain=destDomain;		\n" +
"}\n" +
"\n" +
"$(document).ready(function() {\n" +
"	readTheDataFile('"+this.getTxtfile()+"');\n" +
"});\n" +
"\n" +
"function readTheDataFile(url) {\n" +
"	var logArray = new Array();\n" +
"	$(\"#curDateTime\").append(dateStr.slice(3,24));\n" +
"	$(\"#fileName\").append(url);\n" +
"	\n" +
"	$.ajax(url, {\n" +
"		dataType: 'text',\n" +
"		success: function (data) {\n" +
"			var logStr = data.slice(0,data.length-3); 	// deleting the last ','\n" +
"			logStr = '['+logStr+']';					// putting it within brackets\n" +
"			//console.log(testing);\n" +
"			\n" +
"			$.each($.parseJSON(logStr), function(idx, obj) {\n" +
"				var log = new LogData(obj.date, obj.time, obj.localIP, obj.destDomain);\n" +
"				//console.log(log.toSource());\n" +
"				logArray.push(log);\n" +
"			});\n" +
"			\n" +
"			//displayArrayLength(logArray);\n" +
"			createTable(logArray);\n" +
"			//getDomain('edge-star-shv-13-frc1.facebook.com');\n" +
"		}\n" +
"	});\n" +
"}\n" +
"\n" +
"function displayArrayLength(logArray) {\n" +
"	console.log('After Ajax Array: '+logArray.length);\n" +
"}\n" +
"\n" +
"function createTable(logArray) {\n" +
"	var hashmap = {};	\n" +
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
"	hashmap = buildHashMapByIP(logArray);\n" +
"	\n" +
"	if(hashmap) {\n" +
"		var browsHist = buildBrowsingHistoryPerIP(hashmap);\n" +
"	} else {\n" +
"		alert(\"Sorry, something went seriously wrong!\");\n" +
"	}\n" +
"	\n" +
"	//console.log(hashmap.toSource());\n" +
"	//alert(\"Match Found! \"+getDomain(\"asd-32.12afg.google.com\"));\n" +
"	\n" +
"	$.each(logArray, function(idx, log) {\n" +
"		var row = $('<tr></tr>').addClass('row');\n" +
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
"	$(\"#tableDiv\").append(table);\n" +
"	\n" +
"	$('#logsTable').dataTable( {\n" +
"        \"aaSorting\": [[ 4, \"desc\" ]]\n" +
"    } );\n" +
"	\n" +
"	// Add the Flot version string to the footer\n" +
"	var flotVerP = $('<p></p>').attr('id','flot_ver');\n" +
"	flotVerP.prepend(\"Flot \" + $.plot.version + \" &ndash; \");\n" +
"	$(\"footer\").append(flotVerP);\n" +
"}";
        return this.tableStatFileCntx;
    }
    
}
