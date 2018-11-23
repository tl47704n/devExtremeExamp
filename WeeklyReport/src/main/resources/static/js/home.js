function getMandayDetail(){
	var res;
	$.ajax({
		async: false,
        type: "POST",
        contentType: "application/json",
        url: "/getSRdata",
        data:JSON.stringify({"1":"!"}),
        dataType: 'json',
        success: function (data) {
        		res= data;
          
        }
       
    });
	return res;
}


$(document).ready(function(){

	$("#test").hide();
});
console.log(getMandayDetail());


$(function() {
    var pivotGridChart = $("#pivotgrid-chart").dxChart({
        commonSeriesSettings: {
            type: "bar"
        },
        size: {
            height: 200
        },
        adaptiveLayout: {
            width: 450
        }
    }).dxChart("instance");

    var pivotGrid = $("#pivotgrid").dxPivotGrid({
        allowSortingBySummary: true,
        allowFiltering: true,
        showBorders: true,
        showColumnGrandTotals: false,
        showRowGrandTotals: false,
        showRowTotals: false,
        showColumnTotals: false,
        fieldChooser: {
            enabled: true,
            height: 400
        },
        dataSource: {
            fields: [{
                caption: "BU",
                width: 120,
                dataField: "BU",
                area: "row" ,
                	customizeText : function(cellInfo) {
        			var text = cellInfo.valueText;
        			if (text === "NaN")
        				text = "-";
        			return text;
        		}
            }, {
            	caption:"Year",
                dataField: "Year",
                area: "column" ,
                customizeText : function(cellInfo) {
        			var text = cellInfo.valueText;
        			if (text === "NaN")
        				text = "-";
        			return text;
        		}
            }, {
            	caption:"Month",
                dataField: "Month",
                area: "column",
                customizeText : function(cellInfo) {
        			var text = cellInfo.valueText;
        			if (text === "NaN")
        				text = "-";
        			return text;
        		}
            }, {
            	caption:"Support Group",
                dataField: "Support_Grp",
                area: "row",
                customizeText : function(cellInfo) {
        			var text = cellInfo.valueText;
        			if (text === "NaN")
        				text = "-";
        			return text;
        		}
            }, {
                caption: "Total",
                dataField: "Float_People_Day",
                dataType: "number",
                summaryType: "sum",
                area: "data",
                customizeText : function(cellInfo) {
        			var text = cellInfo.valueText;
        			if (text === "NaN")
        				text = "-";
        			return text;
        		}
            }],

            store: getMandayDetail()
        }
    }).dxPivotGrid("instance");

    pivotGrid.bindChart(pivotGridChart, {
        dataFieldsDisplayMode: "splitPanes",
        alternateDataFields: false
    });

  

});