function getphoto(m) {
    var option;
    var adata = [];
    var bdata = [];
    for(key in m ){
        adata.push(key);

        bdata.push(m[key]);
        if (adata.length>20)break;
    }
    option = {
        title : {
            text: '各城市职位个数',
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['职位个数']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : adata
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'职位个数',
                type:'bar',
                data:bdata,
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均职位数'}
                    ]
                }
            },
        ]
    };

    return option;
}