import requests

r = requests.get('http://restapi.amap.com/v3/place/text?&city=shanghai&output=JSON&offset=20&page=1&key=279ec81517e67f1880c86f09378f9028&extensions=all&types=09&citylimit=true' )
# r.status_code
# 200
# r.headers['content-type']
# 'application/json; charset=utf8'
# >>> r.encoding
# 'utf-8'
# >>> r.text
# u'{"type":"User"...'
# r.json()
# {u'private_gists': 419, u'total_private_repos': 77, ...}

# if 200 != r.status_code:
#     return
page_size = 20
respJO = r.json()
req_cnt = int(respJO['count']) / page_size

pois = []
for i in range(req_cnt):
    r = requests.get(
        'http://restapi.amap.com/v3/place/text?&city=shanghai&output=JSON&offset=20&page=%s&key=279ec81517e67f1880c86f09378f9028&extensions=all&types=09&citylimit=true' % i)
    respJO = r.json()
    # print respJO['address'], respJO['adcode'], respJO['adname'], respJO['citycode'], respJO['cityname'], respJO['location'], respJO['name'],respJO['navi_poiid'], respJO['tag'], respJO['type'], respJO['typecode'], respJO['website']
    pois.extend(respJO['pois'])

# name	address	x	y	telephone
# lon,lat,name,address
for p in pois:
    print p['location'],  ",",  p['name'],  ",",   p['address']
debug = True

#用http://yuntu.amap.com/datamanager/ 先去生成一个地图  手工标注一个点 接着 将这个地图下载下来为cvs文件  然后按照这个cvs文件的格式 将这个脚本输出的数据做好成csv文件再次导入到另一个地图里 就得到上海医院地图了
#生成在线地图里 可以弄出一个链接来