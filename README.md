# KTableView

------

KTableView is a Table,
KTableView nested RecyclerView and RecyclerView nested Custom HorizontalScrollView
it's support：

> * horizontal and vertical scroll
> * swap rows


##screenshot
![ss-1](https://github.com/kHRYSTAL/KTableView/blob/master/screenshoot/screenshoot1.jpg)


##Setup with Android Studio
```
    allprojects {
    		repositories {
    			...
    			maven { url "https://jitpack.io" }
    		}
    	}
```
```
    dependencies {
    	   compile'com.github.kHRYSTAL:KTableView:v0.1.0'
    	}
```

the library minSdkVersion is 15 and targetSdkVersion is 23

------

### Usage

```
		mTableScrollView.setColsNames(columnHeaderNames,columnHeaderWidth,columnHeaderHeight);
        mTableScrollView.setTableData(columnNumber,itemWidth,itemHeight,data);
        mTableScrollView.setLeftTopText("Table",width,height);
        mTableScrollView.setOnRowsSwapListener(new TableScrollView.OnRowsSwapListener() {
            @Override
            public void onRowsSwap(int fromPosition, int toPosition) {
                Toast.makeText(MainActivity.this,"swap from "+ fromPosition+" to " + toPosition,Toast.LENGTH_SHORT).show();
            }
        });

```
###License
```
Copyright (C) 2016 kHRYSTAL

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
