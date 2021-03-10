let index={
    init: function() {
        $("#btn-post").on("click",()=>{
           this.save();
        });

        $("#btn-update").on("click",()=>{
            this.update();
        });

        $("#btn-delete").on("click",()=>{
            this.deleteById();
        });
    },

    save:function() {
        let data={
            title:$("#title").val(),
            content:$("#content").val()
        };
        //console.log(data);

        $.ajax({
            type:"POST",
            url:"/board/api",
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8", //요청의 타입이 뭔지
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("게시글쓰기가 완료되었습니다.");
            location.href="/board";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    update:function() {
        let id=$("#id").val();

        let data={
            title:$("#title").val(),
            content:$("#content").val()
        };
        console.log(data);
        $.ajax({
            type:"PUT",
            url:"/board/api/"+id,
            data:JSON.stringify(data),
            contentType:"application/json; charset=utf-8", //요청의 타입이 뭔지
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("글수정이 완료되었습니다.");
            location.href="/board";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },

    deleteById:function() {
        let id=$("#id").text();
        $.ajax({
            type:"DELETE",
            url:"/board/api/"+id,
            dataType:"json" //응답 데이터의 형식이 뭔지
        }).done(function(resp){
            alert("삭제가 완료되었습니다.");
            location.href="/board";
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
}
index.init();