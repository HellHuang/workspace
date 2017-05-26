/**
 * 
 */

  $(document).ready(function() {
		var gdpDistChart;
		require.config({
		    paths: {
		        echarts: 'http://echarts.baidu.com/build/dist'
		    }
		});
		require(
			[
			    'echarts',
			    'echarts/chart/pie'
			],
			function (ec) {
			
				gdpDistChart = ec.init(document.getElementById('gdpDist'));
		        
		        var gdpDistOption = {
		        	color:['#FF9900','#336699','#339933'],
				    title: {
				        text: 'Investment portfolio',
				        subtext:'(%)',
				        x:'center'
				    },
				    tooltip: {
				        trigger: 'item',
				        formatter: "{a} <br/>{b} : {c} ({d}%)"
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data: ['stock','bond&currency','Others']
				    },
				    toolbox: {
				    	orient: 'vertical',
				    	x:'right',
				    	y:'center',
				        show: true,
				        feature: {
				            mark: {show: true,title:"mark"},
				            dataView: {show: false, readOnly: false,title:"dataview"},
				            magicType: {
				                show: false,
				               
				                type: ['pie', 'funnel'],
				                option: {
				                    funnel: {
				                        x: '25%',
				                        width: '50%',
				                        funnelAlign: 'left',
				                        max: 1548
				                    }
				                }
				            },
				            restore: {show: true,title:"saveData"},
				            saveAsImage: {show: true,title:"savePictures"}
				        }
				    },
				    calculable: true,
				    series: [
				        {
				            name: 'from',
				            type: 'pie',
				            radius: '55%',
				            center: ['50%', '60%'],
				            data: [
				                {value: 0, name: 'test1'},
				                {value: 0, name: 'test2'},
				                {value: 0, name: 'test3'}
				            ]
				        }  //totaldebet  profit
				    ]
				};
				gdpDistChart.setOption(gdpDistOption);
				//gdpDistChart.showLoading();
				gdpDistData();
				gdpDistChart.restore();
			}
		);
		function gdpDistData() {
			var options = gdpDistChart.getOption();
			$.ajax({
				type: "get",
				//async: false,
				url: "testdata",
				dataType: "text",
				data:{
					'code':document.getElementById("code").value
				},
				success: function(data) {
					var macroData = data[0];
					var temp=data.split(",");
					var list = [];
					var data1 = {value:temp[0],name:"stock"};
					var data2 = {value:temp[1],name:"bond&currency"};
					var data3 = {value:temp[2],name:"Others"};
				
					list.push(data2);
					list.push(data1);
					list.push(data3);
					options.series[0].data = list;  
					gdpDistChart.setOption(options); 
					gdpDistChart.hideLoading();
				}
			});
		}
	});
