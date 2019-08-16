var HOST = 'http://localhost:8080'

var seckill = {
    URL: {
        all: function () {
            return HOST + "/product/all";
        },
        exposer: function (productId) {
            return HOST + "/product/" + productId + "/exposer";
        }
    },
    list: function (node) {
        $.get(seckill.URL.all(), {}, function (result) {
            // console.log(result);
            $.each(result, function (index, product) {
                // console.log(product);
                var rowTem =
                    '<tr>' +
                        '<td>'+ product.productId +'</td>' +
                        '<td>'+ product.description +'</td>' +
                        '<td>'+ product.number +'</td>' +
                        '<td>'+ product.startTime.toLocaleString() +'</td>' +
                        '<td>'+ product.endTime.toLocaleString() +'</td>' +
                        '<td><a class="btn btn-primary" href="detail.html?productId='+ product.productId +'">Go</a></td>' +
                    '</tr>';
                $(node).append(rowTem);
            });
        });

    },
    detail: function (node) {
        function getUrlParam(name){
            //构造一个含有目标参数的正则表达式对象
            var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
            //匹配目标参数
            var r = window.location.search.substr(1).match(reg);
            //返回参数值
            if (r!=null) return unescape(r[2]);
            return null;
        }

        var productId = getUrlParam("productId");

        $.post(seckill.URL.exposer(productId), {}, function (result) {
            // console.log(result);
            var tem =
                '<div class="panel panel-default text-center">' +
                    '<div class="panel-heading">' + result.product.description +'</div>' +
                '</div>';
            node.append(tem);
        });
    }
}