/**
 * 
 */

  $(document).ready(function() {
		var IncomeChart;
		require.config({
		    paths: {
		        echarts: 'http://echarts.baidu.com/build/dist'
		    }
		});
		require(
			[
			    'echarts',
			    'echarts/chart/funnel',
			    'echarts/chart/pie'
			],
			function (ec) {
			
				IncomeChart = ec.init(document.getElementById('one'));
		        
		        var incomeOption = {
		        		 color:['#FF9900','#99CC33','#FF6666','#FF6600',],
		        		 title: {
						        text: 'Income in 2016',
						        subtext:'(%)',
						        x:'center'
						    },
		        		tooltip : {
	                        trigger: 'item',
	                        formatter: "{a} <br/>{b} : {c} ({d}%)"
	                    },
	                    legend: {
	                        orient : 'vertical',
	                        x : 'left',
	                        data:['transfer','deposit','fund']
	                    },
	                    toolbox: {
	                        show : true,
	                        feature : {
	                            mark : {show: false},
	                            dataView : {show: false, readOnly: false},
	                            magicType : {
	                                show: true, 
	                                type: ['pie', 'funnel']
	                            },
	                            restore : {show: true},
	                            saveAsImage : {show: true}
	                        }
	                    },
	                    calculable : false,
	                    series : [
	                      
	                        {
	                            name:'source',
	                            type:'pie',
	                            radius : [20, 100],
	                            
	                            // for funnel
	                            x: '60%',
	                            width: '35%',
	                            funnelAlign: 'left',
	                            max: 1048,
	                            
	                            data:[
	                                {value:335, name:'transfer'},
	                               
	                                {value:300, name:'fund'},
	                                 {value:1548, name:'deposit'}
	                            ]
	                        }
	                    ]
	                };
	               // myChart.setOption(option,true); 
				//};
		        IncomeChart.setOption(incomeOption);
				//gdpDistChart.showLoading();
				gdpIncomeData();
				IncomeChart.restore();
			}
		);
		function gdpIncomeData() {
			
			var options = IncomeChart.getOption();
			$.ajax({
				type: "POST",
				//async: false,
				url: "getIncomedata",
				dataType: "text",
				data:{
					'cardNumber':document.getElementById("cardNumber").value
				},
				success: function(data) {
					var macroData = data[0];
					var temp=data.split(",");
					var list = [];
					temp[0]=temp[0].split(":")[1];
					temp[1]=temp[1].split(":")[1];
					temp[2]=temp[2].split(":")[1];
					console.log(temp[0]+temp[1]+temp[2]);
					var data1 = {value:temp[0],name:"transfer"};
					var data2 = {value:temp[1],name:"deposit"};
					var data3 = {value:temp[2],name:"fund"};
				
					list.push(data1);
					list.push(data3);
					list.push(data2);
					options.series[0].data = list;  
					IncomeChart.setOption(options); 
					IncomeChart.hideLoading();
				}
			});
		}
	});
