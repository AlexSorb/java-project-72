@import hexlet.code.dto.url.UrlsPage
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
         <div class="index_block">
            <ul>
            @for(var url : page.getUrlList())
               <li><a href="${NamedRoutes.urlsIdPath(url.getId())}"> ${url.getName()} </a></li>
            @endfor
            </ul>
        </div>
    `
)