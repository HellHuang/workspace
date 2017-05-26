/**
 * 
 */

  $(document).ready(function() {
		var Chart;
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
			
				Chart = ec.init(document.getElementById('two'));
		        
		        var Option = {
		        		 color:['#FF9966','#FF6666','#FF9999','#FF6600','#FF9900','#99CC33','#669999'],
		        		 title: {
						        text: 'Expense in 2016',
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
	                        data:['transfer','bill','gas bill','electricity bill','water bill','fund']
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
	                            selectedMode: 'single',
	                            radius : [0, 80],
	                            
	                            // for funnel
	                            x: '20%',
	                            width: '40%',
	                            funnelAlign: 'right',
	                            max: 1548,
	                            
	                            itemStyle : {
	                                normal : {
	                                	
	                                    label : {
	                                        position : 'inner',
		                                    textStyle:{
		                                    	color:'black',
		                                    	fontSize:14,
		                                    	
		                                    }
	                                    },
	                                    labelLine : {
	                                        show : false
	                                    }
	                                }
	                            },
	                            data:[
	                                {value:335, name:'transfer'},
	                                {value:679, name:'bill'},
	                                {value:300, name:'fund'},
	                              //  {value:1548, name:'time deposit', selected:true}
	                            ]
	                        },
	                        {
	                            name:'source',
	                            type:'pie',
	                            radius : [100, 140],
	                            
	                            // for funnel
	                            x: '60%',
	                            width: '35%',
	                            funnelAlign: 'left',
	                            max: 1048,
	                            
	                            data:[
	                                {value:335, name:'transfer'},
	                                {value:310, name:'gas bill'},
	                                {value:234, name:'electricity bill'},
	                                {value:135, name:'water bill'},
	                                {value:300, name:'fund'},
	                                // {value:1548, name:'time deposit'}
	                            ]
	                        }
	                    ]
	                };
		        //Chart.setOption(option,true); 
				//};
				Chart.setOption(Option);
				//gdpDistChart.showLoading();
				getEData();
				Chart.restore();
			}
		);
		function getEData() {
			var options = Chart.getOption();
			$.ajax({
				type: "POST",
				//async: false,
				url: "getExpensedata",
				dataType: "text",
				data:{
					'cardNumber':document.getElementById("cardNumber").value
				},
				success: function(data) {
					console.log(data);
					var temp=data.split(",");
					var list1 = [];
					var list2 = [];
					temp[0]=temp[0].split(":")[1];
					temp[1]=temp[1].split(":")[1];
					temp[2]=temp[2].split(":")[1];
					temp[3]=temp[3].split(":")[1];
					temp[4]=temp[4].split(":")[1];
					temp[5]=temp[5].split(":")[1];
					temp[6]=temp[6].split(":")[1];
					
					var data0 = {value:temp[0],name:"transfer"};
					var data1 = {value:temp[1],name:"bill"};
					var data2 = {value:temp[2],name:"fund"};
					
					var data3 = {value:temp[3],name:"water bill"};
					var data4 = {value:temp[4],name:"gas bill"};
					var data5 = {value:temp[5],name:"electricity bill"};
					//var data6 = {value:temp[6],name:"time deposit"};
					
					list1.push(data0);
					list1.push(data1);
					list1.push(data2);
					//list1.push(data6);
					
					
					list2.push(data0);
					list2.push(data3);
					list2.push(data4);
					list2.push(data5);
					list2.push(data2);
					//list2.push(data6);
					
					options.series[0].data = list1;  
					options.series[1].data = list2;  
					Chart.setOption(options); 
					Chart.hideLoading();
				}
			});
		}
	});
