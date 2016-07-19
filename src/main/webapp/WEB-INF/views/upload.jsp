<%@ page import="com.kazaf.pojos.GroupMonth" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
  <head>
    <title>读取Excel from WEB-INF/views</title>
  </head>
  
  <body>
  <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <!-- ECharts单文件引入 -->
    <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>

    <script type="text/javascript">

        let ness=new Array();
        let unes=new Array();
        let tota=new Array();
        let month=new Array();
        let num=1;

        <%
          List<GroupMonth> groupMonthList=null;
          groupMonthList=(List<GroupMonth>)request.getSession().getAttribute("GroupMonth");
          if(groupMonthList!=null){
              GroupMonth tempgm;
              Iterator a=groupMonthList.iterator();
              while(a.hasNext()){
                  tempgm=(GroupMonth)a.next();%>
        ness.push(<%=tempgm.getNecessary()%>);
        unes.push(<%=tempgm.getUnnecessary()%>);
        tota.push(<%=tempgm.getTotall()%>);
        month.push(num+'月份');
        num=num+1;
        <%
            }
        }
      %>




     // 路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
    title : {
        text: '2016年消费记录',
        subtext: '个人财务数据'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['必要消费','非必要消费','总消费']
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            data : month
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'必要消费',
            type:'bar',
            data: ness,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        },
        {
            name:'非必要消费',
            type:'bar',
            data: unes,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        },
        {
            name:'总消费',
            type:'bar',
            data: tota,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name : '平均值'}
                ]
            }
        }
    ]
};
                    
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
</script>


    <form action="/uploadservlet"  enctype="multipart/form-data" method="post">
        读取文件：<input type="file" name="file1"><br/>
        <select name="month">
            <option value="1">Jan</option>
            <option value="2">Feb</option>
            <option value="3">Mar</option>
            <option value="4">Apr</option>
            <option value="5">May</option>
            <option value="6">Jun</option>
            <option value="7">Jul</option>
            <option value="8">Aug</option>
            <option value="9">Sep</option>
            <option value="10">Nov</option>
            <option value="11">Dec</option>
            <option value="12">Oct</option>
        </select>

        <input type="submit" value="读取">
    </form>


  </body>
</html>