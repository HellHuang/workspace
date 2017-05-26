/**
 * 
 */

/**
 * 
 */

  $(document).ready(function() {
		var barChart;
		require.config({
		    paths: {
		        echarts: 'http://echarts.baidu.com/build/dist'
		    }
		});
		require(
			[
			    'echarts',
			    'echarts/chart/pie',
			    'echarts/chart/line'
			],
			function (ec) {
			
				barChart = ec.init(document.getElementById('bar'));
		        
		        var gdpDistOption = {
		        	color:['#FF9900','#336699','#339933'],
				    title: {
				        text: 'nav last week',
				        subtext:'',
				        x:'center'
				    },
				    tooltip: {
				    	 trigger: 'axis'
				    },
				    legend: {
				        orient: 'vertical',
				        x: 'left',
				        data: ['nav']
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
				    xAxis : [
				             {
				                 type : 'category',
				                 boundaryGap : false,
				                 
				                 data : [
				                         '06-13',
				                         '06-14',
				                         '06-15',
				                         '06-16',
				                         '06-17',
				                         '06-20',
				                         '06-21'

				                 ],
				                 axisLabel:{
				                 rotate:60,
				                 textStyle:{
				                 	fontSize:1
				                 	}
				                 }
				             }
				         ],
				       yAxis : [
				                {
				                     type : 'value',
				                     max:1.5,
				                     min:0.8,
					                
				                     axisLabel : {
				                         formatter: '{value} '
				                      },

				                  }
				              ],
				    series: [
				        {
				            name: 'nav',
				            type: 'line',
				          
				            data: [0,0,0,0,0,0
				             
				            ]
				        }  //totaldebet  profit
				    ]
				};
		        barChart.setOption(gdpDistOption);
				//gdpDistChart.showLoading();
				getData();
				barChart.restore();
			}
		);
		function getData() {
			var options = barChart.getOption();
			$.ajax({
				type: "get",
				//async: false,
				url: "bardata",
				dataType: "text",
				data:{
					'code':document.getElementById("code").value
				},
				success: function(data) {
					
					var temp=data.split(",");
				
				
						
					
					var list = [];
					var data6 = temp[6].split(":")[1];
					var data5 = temp[5].split(":")[1];
					var data4 = temp[4].split(":")[1];
					var data3 = temp[3].split(":")[1];
					var data2 = temp[2].split(":")[1];
					var data1 = temp[1].split(":")[1];
					var data0 = temp[0].split(":")[1];
					
					list.push(data6);
					list.push(data5);
					list.push(data4);
					list.push(data3);
					list.push(data2);
					list.push(data1);
					list.push(data0);
					console.log(list);
					options.series[0].data = list;  
					barChart.setOption(options); 
					barChart.hideLoading();
				}
			});
		}
	});


