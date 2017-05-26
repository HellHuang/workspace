/**
 * 
 */

  $(document).ready(function() {
		var Chart3;
		require.config({
		    paths: {
		        echarts: 'http://echarts.baidu.com/build/dist'
		    }
		});
		require(
			[
			    'echarts',
			    'echarts/chart/bar',
			    'echarts/chart/line',
			    'echarts/chart/pie'
			],
			function (ec) {
			
				Chart3 = ec.init(document.getElementById('three'));
		        
				var option3 = {
						 title: {
						        text: 'Expense Detail in 2016',
						        subtext:'(%)',
						        x:'center'
						    },
					    tooltip : {         // Option config. Can be overwrited by series or data
					        trigger: 'axis',
					        //show: true,   //default true
					        showDelay: 0,
					        hideDelay: 50,
					        transitionDuration:0,
					        backgroundColor : 'rgba(255,0,255,0.7)',
					        borderColor : '#f50',
					        borderRadius : 8,
					        borderWidth: 2,
					        padding: 10,    // [5, 10, 15, 20]
					        position : function(p) {
					            // 位置回调
					            // console.log && console.log(p);
					            return [p[0] + 10, p[1] - 10];
					        },
					        textStyle : {
					            color: 'yellow',
					            decoration: 'none',
					            fontFamily: 'Verdana, sans-serif',
					            fontSize: 15,
					            fontStyle: 'italic',
					            fontWeight: 'bold'
					        },
					        
					        formatter: "Template formatter: <br/>{b}<br/>{a}:{c}<br/>{a1}:{c1}"
					    },
					    legend: {
	                        orient : 'horizontal',
	                        x : 'center',
	                        y :'bottom',
	                        data:['transfer','withdrawal','fund','gas bill','electricity bill','water bill']
	                    },
					    toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    calculable : true,
					    xAxis : {
					        data : ['2016-01','2016-02','2016-03','2016-04','2016-05','2016-06']
					    },
					    yAxis : {
					        type : 'value'
					    },
					    series : [
					        {
					            name:'transfer',
					            type:'bar',
					            data:[0,0,0,0,0,0
					                
					            ]
					        },
					        {
					            name:'withdrawal',
					            type:'bar',
					            data:[
					                
					            ]
					        },
					        {
					            name:'fund',
					            type:'bar',
					            data:[]
					        },
					        {
					            name:'gas bill',
					            type:'bar',
					            tooltip : {
					                show : false,
					                trigger: 'item'
					            },
					            stack: '数据项',
					            data:[]
					        },
					        {
					            name:'electricity bill',
					            type:'bar',
					            tooltip : {
					                show : false,
					                trigger: 'item'
					            },
					            stack: '数据项',
					            data:[]
					        },
					        {
					            name:'water bill',
					            type:'bar',
					            tooltip : {
					                show : false,
					                trigger: 'item'
					            },
					            stack: '数据项',
					            data:[]
					        },
					    ]
					};
				//Chart3.setOption(option,true); 
				//};
				Chart3.setOption(option3);
				//gdpDistChart.showLoading();
				Data3();
				Chart3.restore();
			}
		);
		function Data3() {
			var options = Chart3.getOption();
			$.ajax({
				type: "POST",
				//async: false,
				url: "getDetaildata",
				dataType: "text",
				data:{
					'cardNumber':document.getElementById("cardNumber").value
				},
				success: function(data) {
					var macroData = data[0];
					console.log(data);
					data=data.replace("[","");
					data=data.replace("]","");
					console.log(data);
					data=data.split(",");
					var start=0;
					var end=5;
					var list1 = [];var list2 = [];var list3 = [];var list4 = [];var list5 = [];var list6 = [];
					for(i=start;i<=end;i++){
						list1.push(data[i]);
					}
					start=6;end=11;
					for(i=start;i<=end;i++){
						list2.push(data[i]);
					}
					start=12;end=17;
					for(i=start;i<=end;i++){
						list3.push(data[i]);
					}
					start=18;end=23;
					for(i=start;i<=end;i++){
						list4.push(data[i]);
					}
					start=24;end=29;
					for(i=start;i<=end;i++){
						list5.push(data[i]);
					}
					start=30;end=35;
					for(i=start;i<=end;i++){
						list6.push(data[i]);
					}
//					var temp=data.split(",");
//					var list = [];
//					var data1 = {value:temp[0],name:"stock"};
//					var data2 = {value:temp[1],name:"bond&currency"};
//					var data3 = {value:temp[2],name:"Others"};
//				
//					list.push(data2);
//					list.push(data1);
//					list.push(data3);
					options.series[0].data = list1;  
					options.series[1].data = list2;
					options.series[2].data = list3;
					options.series[3].data = list4;
					options.series[4].data = list5;
					options.series[5].data = list6;
					Chart3.setOption(options); 
					Chart3.hideLoading();
				}
			});
		}
	});
/**
 * 
 */