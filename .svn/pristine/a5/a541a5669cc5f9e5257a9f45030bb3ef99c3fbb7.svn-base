// JavaScript Document
/*
 n===  为总毫秒数，并必须是数组
 ele===为对象
 */
function numToDate(n,ele,format,fn){
    var timer = setInterval(countTine,100);
    function countTine(){
        if(n[0]<100){
            clearInterval(timer);
            ele.innerHTML="剩余0天0小时0分钟0秒";
            if(fn){
                fn();
            }
            return false;
        }
        if(n[0]>100){
            n[0]-=100;//每次减少100毫秒
            var num = n[0];//毫秒总时间（必须为对象）
            var day = 1000*60*60*24;//天的毫秒数
            var hour = 1000*60*60;//小时的毫秒数
            var minute = 1000*60;//分钟的毫秒数
            var second = 1000;//秒的毫秒数

            //天
            var setDay=Math.floor(num/day);//计算天
            var remainder=num%day;//计算不足一天的剩余毫秒数
            //时
            var setHour=Math.floor(remainder/hour);//计算小时
            var remainder=remainder%hour;//计算不足一小时的剩余毫秒数
            //分钟
            var setMinute=Math.floor(remainder/minute);//计算分钟
            var remainder=remainder%minute;
            //秒
            var setSecond=Math.floor(remainder/second);//计算秒
            var remainder=remainder%second;
            //毫秒
            var millisecond=Math.floor(remainder/100);//计算毫秒
            var remainder=remainder%100;

            if(setHour<10){//设置个位数的小时
                setHour='0'+setHour;
            }
            if(setMinute<10){//设置个位数的分钟
                setMinute='0'+setMinute;
            }
            if(setSecond<10){//设置个位数的秒
                setSecond='0'+setSecond;
            }
            if(!format){
                format='剩余{d}天{h}小时{m}分钟{s1}.{s2}秒';
            }

            ele.innerHTML=format
                .replace('{d}',setDay)
                .replace('{h}',setHour)
                .replace('{m}',setMinute)
                .replace('{s1}',setSecond)
                .replace('{s2}',millisecond);

            return true;
        }
    }
    return countTine();
}
