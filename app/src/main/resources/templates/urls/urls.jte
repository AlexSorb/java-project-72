@import hexlet.code.dto.url.UrlsPage
@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
         <div class="index_block">
            <ul>
            @for(var url : page.getUrlList())
               <li><a href="/urls/${url.getId()}"> ${url.getName()} </a></li>
            @endfor
            </ul>
        </div>
    `
)