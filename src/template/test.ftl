
public class TranslateView{

private static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
private static final int WRAP_CONTENT = ViewGroup.LayoutParams.WRAP_CONTENT;
private static final int FILL_PARENT = ViewGroup.LayoutParams.FILL_PARENT;

	public void view(Context mContext){
	
<#assign n=1/>
<#assign parentName="content"/>

${root.nodeName}  ${parentName}= new ${root.nodeName}(mContext);
<#assign params = "params${n}"/>
  <#if root.viewBean.layout_width??>
    ${root.paramName}.LayoutParams ${params} = new ${root.paramName}.LayoutParams(
    ${root.viewBean.layout_width}, ${root.viewBean.layout_height});
  </#if>

<#macro buildNode child parent parentInstance>
    <#if child?? && child?size gt 0>
        <#list child as t>
        
<#-- --------------init------------------------------ -->        
    <#assign content_name ="view${n}"/>
  <#if t.nodeName=="include">
  
  <#else>
    ${t.nodeName} ${content_name} = new ${t.nodeName}(mContext);
  </#if>
<#-- ----------------ID---------------------------- -->
  <#if t.viewBean.id??>
    int ${t.viewBean.id} = ${100+n};
    ${content_name}.setId(${t.viewBean.id});
  </#if>
<#-- ----------------scrollbars---------------------------- -->
  <#if t.viewBean.scrollbars??>
  	<#if t.viewBean.scrollerbars=="horizontal">
  	${content_name}.setHorizontalScrollBarEnabled(true);
  	<#elseif t.viewBean.scrollerbars=="vertical">
  	${content_name}.setVerticalScrollBarEnabled(true);
  	<#elseif t.viewBean.scrollerbars=="none">
  	${content_name}.setVerticalScrollBarEnabled(false);
  	${content_name}.setHorizontalScrollBarEnabled(false);
  	</#if>
  </#if>
<#-- --------------else---------------------------------- -->
<#if t.viewBean.mapValues??>
	<#list t.viewBean.mapValues?keys as key>
    ${content_name}${t.viewBean.mapValues[key]};
	</#list>
</#if>
<#-- ------------------------------------------------ -->
<#if t.viewBean.mapUnPrased??>
	<#list t.viewBean.mapUnPrased?keys as key>
    // android:${key}=${t.viewBean.mapUnPrased[key]};//未解析
	</#list>
</#if>  
<#-- *************LayoutParams************************ -->
 <#assign params = "params${n}"/>
  <#if t.viewBean.layout_width??>
    ${parent.paramName}.LayoutParams ${params} = new ${parent.paramName}.LayoutParams(
    ${t.viewBean.layout_width}, ${t.viewBean.layout_height});
  </#if>
<#-- -------------else----------------------------------- -->
<#if t.viewBean.mapParamsValues??>
	<#list t.viewBean.mapParamsValues?keys as key>
	${params}${t.viewBean.mapParamsValues[key]};
	</#list>
</#if>
<#-- -------------RelativeLayout.addRule----------------- -->
<#if t.viewBean.mapRelaRules??>
  <#list t.viewBean.mapRelaRules?keys as key> 
    ${params}.addRule(RelativeLayout.${key},
    ${t.viewBean.mapRelaRules[key]});
  </#list>
</#if>
<#-- -------------------------------------------- -->
<#if t.nodeName=="include">
  
<#else>
  <#if t.viewBean.layout_width??>
    ${parentInstance}.addView(view${n},${params});
  <#else>
    ${parentInstance}.addView(view${n});
  </#if>
</#if>

<#assign n=n+1/>
<#assign parentName="view${n-1}"/>
			
            <@buildNode child=t.Key_List parent=t parentInstance=parentName/>
        </#list>
    </#if>
</#macro>  

<@buildNode child=root.Key_List parent=root parentInstance=parentName/>

	}



}